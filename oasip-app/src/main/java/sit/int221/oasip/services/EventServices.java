package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.AddEventDetailDTO;
import sit.int221.oasip.dtos.EditEventDTO;
import sit.int221.oasip.dtos.EventDetailDTO;
import sit.int221.oasip.dtos.SimpleEventDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventCategoryRepository;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.repositories.StatusRepository;
import sit.int221.oasip.utils.ListMapper;


import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
                Sort.by("eventDate").descending().and(Sort.by("eventStartTime").descending()))
                , SimpleEventDTO.class, modelMapper);
    }

    public EventDetailDTO getEventById(Integer id ){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, id + "does not exist."));
        return modelMapper.map(event, EventDetailDTO.class);
    }


    public Event save(AddEventDetailDTO newEvent){
        Event event = modelMapper.map(newEvent, Event.class);
        event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND)));

        event.setStatus(statusRepository.findById(3).orElseThrow());
        //Add to end time
        LocalTime endTime = newEvent.getEventStartTime().toLocalTime().plusMinutes((long) newEvent.getEventDuration());
        event.setEventEndTime(Time.valueOf(endTime));

        return eventRepository.saveAndFlush(event);
    }

    public void delete(Integer id) { eventRepository.deleteById(id);}

    public Event update(Integer id , EditEventDTO editEventDTO){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Set new details
        event.setEventNotes(editEventDTO.getEventNotes());
        event.setEventDate(editEventDTO.getEventDate());
        event.setEventStartTime(editEventDTO.getEventStartTime());
        LocalTime endTime = event.getEventStartTime().toLocalTime().plusMinutes((long) event.getEventDuration());
        event.setEventEndTime(Time.valueOf(endTime));

        return eventRepository.saveAndFlush(event);
    }
}
