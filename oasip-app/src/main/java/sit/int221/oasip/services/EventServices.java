package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.AddEventDetailDTO;
import sit.int221.oasip.dtos.EventDetailDTO;
import sit.int221.oasip.dtos.SimpleEventDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventCategoryRepository;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.utils.ListMapper;


import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventServices {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final ListMapper listMapper;
    private final EventCategoryRepository eventCategoryRepository;

    public EventServices(EventRepository eventRepository, ModelMapper modelMapper, ListMapper listMapper, EventCategoryRepository eventCategoryRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    public List<SimpleEventDTO> getAllEvents() {
        return listMapper.mapList(eventRepository.findAll(), SimpleEventDTO.class, modelMapper);
    }

    public EventDetailDTO getEventById(Integer id ){
        Event event = eventRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, id + "does not exist."));
        return modelMapper.map(event, EventDetailDTO.class);
    }


    public Event save(AddEventDetailDTO newEvent){
        Event event = modelMapper.map(newEvent, Event.class);
        event.setEventCategory(eventCategoryRepository.findById(newEvent.getCategoryId()));

        //Add to end time
        LocalTime endTime = newEvent.getEventStartTime().toLocalTime().plusMinutes((long) newEvent.getEventDuration());
        event.setEventEndTime(Time.valueOf(endTime));

        return eventRepository.saveAndFlush(event);
    }

}
