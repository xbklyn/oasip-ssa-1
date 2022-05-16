package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.EventDetailDTO;
import sit.int221.oasip.dtos.PostEventDTO;
import sit.int221.oasip.dtos.PutEventDTO;
import sit.int221.oasip.dtos.SimpleEventDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventCategoryRepository;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.repositories.StatusRepository;
import sit.int221.oasip.utils.ListMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
       return listMapper.mapList(eventRepository.findAll(
                Sort.by("eventStartTime").descending()
        ), SimpleEventDTO.class, modelMapper);
    }

    public EventDetailDTO getEventById(Integer id ){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND, id + "does not exist."));
        return modelMapper.map(event, EventDetailDTO.class);
    }

    // POST Method
    // Save new event

    public Event save(PostEventDTO newEvent){
        Event event = modelMapper.map(newEvent, Event.class);
        event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()).orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND)));

        event.setStatus(statusRepository.findById(3).orElseThrow());
        event.setEventDuration(event.getEventCategory().getEventCategoryDuration());
        //Add to end time
        LocalDateTime endTime = newEvent.getEventStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plus(Duration.of(event.getEventDuration(), ChronoUnit.MINUTES));
        event.setEventEndTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

        return eventRepository.saveAndFlush(event);
    }

    // DELETE Method
    // Delete Existing Event
    public void delete(Integer id) { eventRepository.deleteById(id);}

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

        return eventRepository.saveAndFlush(event);
    }
}
