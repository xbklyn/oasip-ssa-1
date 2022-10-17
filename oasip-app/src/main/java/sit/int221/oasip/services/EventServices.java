package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.Email.EmailDTO;
import sit.int221.oasip.dtos.event.*;
import sit.int221.oasip.dtos.time.TimeDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.entities.EventOwner;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.properties.FileProperties;
import sit.int221.oasip.repositories.*;
import sit.int221.oasip.utils.ListMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Transactional
@Service
public class EventServices {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final ListMapper listMapper;
    private final EventCategoryRepository eventCategoryRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EmailService emailService;
    private final FileService fileService;
    private final FileProperties fileProperties;

    public EventServices(EventRepository eventRepository, ModelMapper modelMapper, ListMapper listMapper, EventCategoryRepository eventCategoryRepository, StatusRepository statusRepository, UserRepository userRepository, EventOwnerRepository eventOwnerRepository, EmailService emailService, FileService fileService, FileProperties fileProperties) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
        this.eventCategoryRepository = eventCategoryRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.eventOwnerRepository = eventOwnerRepository;
        this.emailService = emailService;
        this.fileService = fileService;
        this.fileProperties = fileProperties;
    }

    // GET
    public List<SimpleEventDTO> getAllEvents(Authentication auth) {
        check();

        if(auth == null) throw new ResponseStatusException(UNAUTHORIZED , "Access Denied");
        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = auth.getPrincipal().toString();

        switch (role) {
            case "lecturer": {
                System.out.println("In lecturer case");
                User user = userRepository.findByUserEmail(String.valueOf(auth.getPrincipal())).get(0);
                List<EventOwner> eventOwner = eventOwnerRepository.findByUser(user);
                List<Event> events = new ArrayList<>();
                eventOwner.forEach(e -> {
                    List<Event> eventList = eventRepository.findByEventCategory(e.getEventCategory());
                    events.addAll(eventList);
                });
                return listMapper.mapList(events, SimpleEventDTO.class, modelMapper);
            }
            case "admin": {
                return listMapper.mapList(eventRepository.findAll(
                        Sort.by("eventStartTime").descending()
                ), SimpleEventDTO.class, modelMapper);
            }
            case "student" : {
                List<Event> events = eventRepository.getByUserEmail(String.valueOf(auth.getPrincipal()));
                return listMapper.mapList(events, SimpleEventDTO.class , modelMapper);
            }
        }
        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong.");
    }

    public ResponseEntity getEventById(Integer id, Authentication auth ) throws IOException {

        if(auth == null) throw new ResponseStatusException(UNAUTHORIZED , "Access Denied");
        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = String.valueOf(auth.getPrincipal());

        //Check role if admin
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, id + "does not exist."));
        String user_dir = event.getUser() == null ? "guest" : "user/" + "user_" + event.getUser().getId();
        Path filePath = Paths.get(fileProperties.getUpload_dir() + "/" + user_dir + "/" + "event_" + event.getBookingId());

        switch (role) {
            case "lecturer" : {
                return eventOwnerRepository.findByEventCategoryAndUser(event.getEventCategory() , userRepository.findByUserEmail(email).get(0)) != null
                    ? ResponseEntity.status(200).body(modelMapper.map(event, DetailEventDTO.class))
                    : ResponseEntity.status(403).body("Access Denied");
            }
            case "admin" : {
                return getEventResponseEntity(event, filePath);
            }

            case "student" : {
                if(!eventRepository.findById(id).orElseThrow().getBookingEmail().equals(userRepository.findByUserEmail(email).get(0).getUserEmail()))
                {return ResponseEntity.status(403).body("Access Denied");}

                return getEventResponseEntity(event, filePath);
            }
        }

        throw new ResponseStatusException(FORBIDDEN , "Something went wrong.");
    }

    private ResponseEntity getEventResponseEntity(Event event, Path filePath) throws IOException {
        Map<String, String> file = checkFile(String.valueOf(filePath));
        if(file.isEmpty()){
            System.out.println("No file");
            return ResponseEntity.status(200).body(modelMapper.map(event , DetailEventDTO.class));

        }else{
            DetailEventWithFileDTO detailEventWithFileDTO = modelMapper.map(event,DetailEventWithFileDTO.class);
            detailEventWithFileDTO.setFileName(file.get("fileName"));
            detailEventWithFileDTO.setFileURL(file.get("fileURL"));
            System.out.println("Have file");
            return ResponseEntity.status(200).body(detailEventWithFileDTO);
        }
    }

    private Map<String , String> checkFile(String directories) throws IOException {
        Map<String,String> fileInfo = new HashMap<>();
        if(Files.exists(Path.of(directories))){
            //Get a file
            Path toFile = Files.list(Path.of(directories)).collect(Collectors.toList()).get(0);

            //Create a file URL
            URL fileURL = new URL(fileProperties.getHost() + toFile);
            String fileName = fileService.loadFileAsResource(String.valueOf(toFile)).getFilename();
            fileInfo.put("fileURL", String.valueOf(fileURL));
            fileInfo.put("fileName" , fileName);
        }
        return fileInfo;
    }

    public List<TimeDTO> getEventByCatIdAndDate(Integer catId , String date){
        List<Event> event = eventRepository.getByCategoryAndDate(catId, date);
        return listMapper.mapList(event , TimeDTO.class , modelMapper);
    }

    // POST
    public ResponseEntity save(PostEventDTO newEvent, MultipartFile file , HttpServletRequest req, Authentication auth) throws MethodArgumentNotValidException, ParseException {

        String role =  auth == null ? "" : String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = auth == null ? "" : auth.getPrincipal().toString();

        switch (role) {
            case "lecturer":
                return ResponseEntity.status(403).body("Access Denied");
            case "student": {
                if (!newEvent.getBookingEmail().equals(email))
                    return ResponseEntity.status(400).body("Booking email must be the same as the student's email!");

                Event created_event = createEvent(newEvent , file);
                if(file != null) {fileService.store(file ,created_event);}
                sendEmail(created_event);
                return ResponseEntity.status(201).body("Sucessfully Created!");
            }
            default: {
                System.out.println("In default case");
                Event created_event = createEvent(newEvent, file);
                System.out.println("file : " + file.getOriginalFilename());
                if(file != null) {fileService.store(file ,created_event);}
                sendEmail(created_event);
                return ResponseEntity.status(201).body("Sucessfully Created!");
            }
        }
    }

    private Event createEvent(PostEventDTO newEvent ,MultipartFile file){
        //Check future date
        if(newEvent.getEventStartTime().before(new Date()))
            throw new ResponseStatusException(BAD_REQUEST, "Time must be in a future");

        //Check valid Email
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(!newEvent.getBookingEmail().matches(regex))
            throw new ResponseStatusException(BAD_REQUEST, "Email must be a well-formed email address");

        Event event = modelMapper.map(newEvent, Event.class);

        event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND)));
        event.setStatus(statusRepository.findById(3).orElseThrow());
        event.setEventDuration(event.getEventCategory().getEventCategoryDuration());

        //Add to end time
        LocalDateTime endTime = newEvent.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
        event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

        User user = userRepository.findByUserEmail(newEvent.getBookingEmail()).size() != 0
                ? userRepository.findByUserEmail(newEvent.getBookingEmail()).get(0)
                : null;

        event.setUser(user);
        return eventRepository.saveAndFlush(event);
    }

    private void sendEmail(Event newEvent) throws ParseException {
        System.out.println("In sending email");
        EmailDTO details = new EmailDTO();
        String start = DateTimeFormatter.ofPattern("E MMM dd, yyyy HH:mm").withZone(ZoneId.of("Asia/Bangkok")).format(newEvent.getEventStartTime().toInstant());
        String end = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.of("Asia/Bangkok")).format(newEvent.getEventEndTime().toInstant());

        details.setSubject("[OASIP] " + newEvent.getEventCategory().getEventCategoryName() + " @ " + start + " - " + end + " (ICT)");
        details.setRecipient(newEvent.getBookingEmail());
        details.setMsgBody("Booking name :: " + newEvent.getBookingName() +
                        "\nClinic :: " + newEvent.getEventCategory().getEventCategoryName() +
                        "\nWhen :: " + start + " - " + end + " (ICT)" +
                        "\nNotes :: " + newEvent.getEventNotes());

        emailService.sendSimpleMail(details);
    }

    // DELETE
    public ResponseEntity delete(Integer id, Authentication auth) {

        if(auth == null) throw new ResponseStatusException(UNAUTHORIZED , "Access Denied");

        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = auth.getPrincipal().toString();

        switch (role) {
            case "lecturer" :
                return ResponseEntity.status(403).body("Access Denied");
            case "admin" : {
                eventRepository.deleteById(id);
                check();
                return ResponseEntity.status(200).body("Successfully deleted!");
            }
            case "student" : {
                if(!email.equals(eventRepository.findById(id).orElseThrow().getUser().getUserEmail())) return ResponseEntity.status(403).body("Access Denied");
                eventRepository.deleteById(id);
                check();
                return ResponseEntity.status(200).body("Successfully deleted!");
            }
        }
        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    // PUT
    public ResponseEntity update(Integer id , PutEventDTO editEvent , Authentication auth){

        if(auth == null) throw new ResponseStatusException(UNAUTHORIZED , "Access Denied");

        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = auth.getPrincipal().toString();

        Event event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND));

        switch (role) {
            case "lecturer" :
                return ResponseEntity.status(403).body("Access Denied");
            case "admin" : {

                return ResponseEntity.status(200).body(updateEvent(id,editEvent,event));
            }
            case "student" : {
                if(!email.equals(event.getUser().getUserEmail())) return ResponseEntity.status(403).body("Access Denied");
                return ResponseEntity.status(200).body(updateEvent(id,editEvent,event));
            }
        }

        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    public void check(){
        eventRepository.checkStatusOngoing();
        eventRepository.checkStatusComplete();
    }

    private Event updateEvent(Integer id , PutEventDTO detail , Event event){

        event.setEventNotes(detail.getEventNotes());

        if (detail.getEventStartTime().getTime() != event.getEventStartTime().getTime()) {
            event.setEventStartTime(detail.getEventStartTime());
            LocalDateTime endTime = event.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
            event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));
        }

        check();
        return eventRepository.saveAndFlush(event);
    }
    // CHECK OVERLAP
    private boolean checkIsOverlap(Event PostEvent) {
        List<Event> allEvent = eventRepository.getByCategoryAndDate(
                //Category Id
                PostEvent.getEventCategory().getCategoryId() ,
                //String Date
                LocalDate.from(PostEvent.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).toString()
        );
        //Check with Start Time
        if(!eventRepository.findByEventStartTimeAndEventCategory_CategoryId(PostEvent.getEventStartTime() , PostEvent.getEventCategory().getCategoryId()).isEmpty()) {
            return true;
        }
        //Check overall
        AtomicBoolean isOverLap = new AtomicBoolean(false);
        allEvent.forEach(e2 -> {
            if (
              //Check Outside e2
              (PostEvent.getEventStartTime().getTime() < e2.getEventStartTime().getTime() && PostEvent.getEventEndTime().getTime() > e2.getEventEndTime().getTime()) ||
              //Check Inside e2
              (PostEvent.getEventStartTime().getTime() > e2.getEventStartTime().getTime() && PostEvent.getEventEndTime().getTime() < e2.getEventEndTime().getTime()) ||
              //Check Between
              (PostEvent.getEventStartTime().after(e2.getEventStartTime()) && PostEvent.getEventStartTime().before(e2.getEventEndTime())) ||
              (PostEvent.getEventEndTime().after(e2.getEventStartTime()) && PostEvent.getEventEndTime().before(e2.getEventEndTime()))
            ){
                isOverLap.set(true);
            }
        });
        return isOverLap.get();
    }

}
