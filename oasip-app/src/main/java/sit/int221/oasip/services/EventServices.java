package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.event.*;
import sit.int221.oasip.dtos.time.TimeDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.entities.EventOwner;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.*;
import sit.int221.oasip.utils.ListMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.http.HttpStatus.*;

@Transactional
@Service
public class EventServices {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final ListMapper listMapper;
    private final EventCategoryRepository eventCategoryRepository;
    private final StatusRepository statusRepository;
    private final ErrorAdvice errorAdvice;
    private final UserRepository userRepository;
    private final EventOwnerRepository eventOwnerRepository;

    public EventServices(EventRepository eventRepository, ModelMapper modelMapper, ListMapper listMapper, EventCategoryRepository eventCategoryRepository, StatusRepository statusRepository, ErrorAdvice errorAdvice, UserRepository userRepository, EventOwnerRepository eventOwnerRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
        this.eventCategoryRepository = eventCategoryRepository;
        this.statusRepository = statusRepository;
        this.errorAdvice = errorAdvice;
        this.userRepository = userRepository;
        this.eventOwnerRepository = eventOwnerRepository;
    }

    // GET
    public List<SimpleEventDTO> getAllEvents(Authentication auth) {
        check();
        if(String.valueOf(auth.getAuthorities().toArray()[0]).equals("lecturer")){
            User user = userRepository.findByUserEmail(String.valueOf(auth.getPrincipal())).get(0);
            List<EventOwner> eventOwner = eventOwnerRepository.findByUser(user);
            List<Event> events = new ArrayList<>();
            eventOwner.forEach(e -> {
                List<Event> eventList = eventRepository.findByEventCategory(e.getEventCategory());
                events.addAll(eventList);
            });
            return listMapper.mapList(events , SimpleEventDTO.class , modelMapper);
        }
        if(String.valueOf(auth.getAuthorities().toArray()[0]).equals("admin")) {
            return listMapper.mapList(eventRepository.findAll(
                    Sort.by("eventStartTime").descending()
            ), SimpleEventDTO.class, modelMapper);
        }

        List<Event> events = eventRepository.getByUserEmail(String.valueOf(auth.getPrincipal()));
        return listMapper.mapList(events, SimpleEventDTO.class , modelMapper);
    }

    public ResponseEntity getEventById(Integer id, Authentication auth ){
//        Event event_test = eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "not found"));
//        System.out.println(event_test.getUser().getUserName());
        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = String.valueOf(auth.getPrincipal());
        //Check role if admin
        if(role.equals("admin") || role.equals("lecturer")){
            Event event = eventRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(NOT_FOUND, id + "does not exist."));

            if(role.equals("lecturer")){
                User user = userRepository.findByUserEmail(email).get(0);
                EventOwner eventOwners = eventOwnerRepository.findByEventCategoryAndUser(event.getEventCategory() , user);
                return eventOwners == null
                        ? ResponseEntity.status(403).body("Access Denied")
                        : Objects.equals(eventOwners.getUser().getId(), user.getId()) && Objects.equals(eventOwners.getEventCategory().getCategoryId(), event.getEventCategory().getCategoryId())
                            ? ResponseEntity.status(200).body(modelMapper.map(event, DetailEventDTO.class))
                            : ResponseEntity.status(403).body("Access Denied!");
            }
            if(event.getUser() == null){
                return ResponseEntity.status(200).body(modelMapper.map(event, GuestEventDTO.class));
            }
            check();
            return ResponseEntity.status(200).body(modelMapper.map(event, DetailEventDTO.class));
        }

        //Check user id is equals to user id in event
        if(!eventRepository.findById(id).orElseThrow().getUser().getId().equals(userRepository.findByUserEmail(email).get(0).getId())){
            Map<String , String > error = new HashMap<>();
            error.put("Unauthorized" , "Access Denied.");
            return ResponseEntity.status(403).body(error);
        }

        return ResponseEntity.status(200).body(modelMapper.map(eventRepository.findById(id).get(), DetailEventDTO.class));

    }

    public List<TimeDTO> getEventByCatIdAndDate(Integer catId , String date){
        List<Event> event = eventRepository.getByCategoryAndDate(catId, date);
        return listMapper.mapList(event , TimeDTO.class , modelMapper);
    }

    // POST
    public ResponseEntity save(PostEventDTO newEvent, HttpServletRequest req, Authentication auth) throws MethodArgumentNotValidException {

        if(String.valueOf(auth.getAuthorities().toArray()[0]).equals("admin") || newEvent.getBookingEmail().equals(String.valueOf(auth.getPrincipal()))) {
            //Check future date
            if(newEvent.getEventStartTime().before(new Date()))
                return ResponseEntity.status(400).body(errorAdvice.getResponseEntity("eventStartTime" , "Time must be in a future" , req));
            //Check valid Email
            String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            if(!newEvent.getBookingEmail().matches(regex))
                return ResponseEntity.status(400).body(errorAdvice.getResponseEntity("bookingEmail" , "must be a well-formed email address" , req));

            Event event = modelMapper.map(newEvent, Event.class);
            event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()).orElseThrow(() ->
                    new ResponseStatusException(NOT_FOUND)));
            event.setStatus(statusRepository.findById(3).orElseThrow());
            event.setEventDuration(event.getEventCategory().getEventCategoryDuration());
            //Add to end time
            LocalDateTime endTime = newEvent.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
            event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

            User user = userRepository.findByUserEmail(String.valueOf(newEvent.getBookingEmail())).get(0) == null ? userRepository.findByUserEmail(String.valueOf(auth.getPrincipal())).get(0) : userRepository.findByUserEmail(String.valueOf(newEvent.getBookingEmail())).get(0);
            event.setUser(user);
            //Check Overlap
            if(checkIsOverlap(event))
                return ResponseEntity.status(400).body(errorAdvice.getResponseEntity("eventStartTime" , "Time is overlapping" , req));
            check();
            return ResponseEntity.status(201).body(eventRepository.saveAndFlush(event));
        }
        return ResponseEntity.status(400).body("booking email must be the same as the student's email");
    }

    // DELETE
    public ResponseEntity delete(Integer id, Authentication auth) {
        if(String.valueOf(auth.getAuthorities().toArray()[0]).equals("admin") || eventRepository.findById(id).orElseThrow().getUser().getUserEmail().equals(String.valueOf(auth.getPrincipal()))){
            eventRepository.deleteById(id);
            check();
            return ResponseEntity.status(200).body("Delete sucessfully!");
        }
        Map<String, String> error = new HashMap<>();
        error.put("Unauthorized","Access Denied.");
        return ResponseEntity.status(403).body(error);
    }

    // PUT
    public ResponseEntity update(Integer id , PutEventDTO editEvent , Authentication auth){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND));

        if(String.valueOf(auth.getAuthorities().toArray()[0]).equals("admin") || event.getUser().getUserEmail().equals(String.valueOf(auth.getPrincipal()))){
            //  Set new details
            event.setEventNotes(editEvent.getEventNotes());
            if (editEvent.getEventStartTime().getTime() != event.getEventStartTime().getTime()) {
                event.setEventStartTime(editEvent.getEventStartTime());
                LocalDateTime endTime = event.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
                event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));
            }

            check();
            return ResponseEntity.status(200).body(eventRepository.saveAndFlush(event));
        }
        Map<String, String> error = new HashMap<>();
        error.put("Unauthorized","Access Denied.");
        return ResponseEntity.status(403).body(error);
    }

    public void check(){
        eventRepository.checkStatusOngoing();
        eventRepository.checkStatusComplete();
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
