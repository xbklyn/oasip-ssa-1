package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.*;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.errors.ApiError;
import sit.int221.oasip.repositories.EventCategoryRepository;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.repositories.StatusRepository;
import sit.int221.oasip.utils.ListMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Transactional
@Service
public class EventServices {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final ListMapper listMapper;
    private final EventCategoryRepository eventCategoryRepository;
    private final StatusRepository statusRepository;

    public EventServices(EventRepository eventRepository, ModelMapper modelMapper, ListMapper listMapper, EventCategoryRepository eventCategoryRepository, StatusRepository statusRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
        this.eventCategoryRepository = eventCategoryRepository;
        this.statusRepository = statusRepository;
    }

    public List<SimpleEventDTO> getAllEvents() {
        System.out.println(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
       check();
       return listMapper.mapList(eventRepository.findAll(
                Sort.by("eventStartTime").descending()
        ), SimpleEventDTO.class, modelMapper);
    }

    public EventDetailDTO getEventById(Integer id ){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND, id + "does not exist."));
        check();
        return modelMapper.map(event, EventDetailDTO.class);
    }

    public List<TimeDTO> getEventByCatIdAndDate(Integer catId , String date){
        List<Event> event = eventRepository.getByCategoryAndDate(catId, date);
        return listMapper.mapList(event , TimeDTO.class , modelMapper);
    }

    // POST Method
    // Save new event

    public ResponseEntity save(PostEventDTO newEvent, HttpServletRequest req) throws MethodArgumentNotValidException {

        //Check Overlap
        List<Event> overlapEvent = eventRepository.findByEventStartTimeAndEventCategory_CategoryId(newEvent.getEventStartTime() , newEvent.getCategoryId());
        if(!overlapEvent.isEmpty()) {
            return ResponseEntity.status(400).body("Time is overlapping");
        }
        //Check future date
        if(newEvent.getEventStartTime().before(new Date())) return ResponseEntity.status(400).body("Time must be in a future");

        Event event = modelMapper.map(newEvent, Event.class);
        event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND)));

        event.setStatus(statusRepository.findById(3).orElseThrow());
        event.setEventDuration(event.getEventCategory().getEventCategoryDuration());
        //Add to end time
        LocalDateTime endTime = newEvent.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
        event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

        check();
        return ResponseEntity.status(201).body(eventRepository.saveAndFlush(event));
    }

    // DELETE Method
    // Delete Existing Event
    public void delete(Integer id) {
        eventRepository.deleteById(id);
        check();
    }

    // PUT Method
    // Edit Existing Event
    public Event update(Integer id , PutEventDTO editEvent){
        // Find an event to edit
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND));

//      Set new details
        event.setEventNotes(editEvent.getEventNotes());
        event.setEventStartTime(editEvent.getEventStartTime());
        LocalDateTime endTime = event.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
        event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

        check();
        return eventRepository.saveAndFlush(event);
    }

    public void check(){
        eventRepository.checkStatusOngoing();
        eventRepository.checkStatusComplete();
    }
}
