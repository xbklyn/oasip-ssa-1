package sit.int221.oasip.controllers;

import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import sit.int221.oasip.dtos.AddEventDetailDTO;
import sit.int221.oasip.dtos.EventDetailDTO;
import sit.int221.oasip.dtos.SimpleEventDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.services.EventServices;

import java.util.List;

// @CrossOrigin(origins = "http://intproj21.sit.kmutt.ac.th:80/ssa1")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ssa1/api/events")
public class EventsController {

    private final EventServices eventServices;

    public EventsController(EventRepository erepo, EventServices eventServices) {
        this.eventServices = eventServices;
    }

//  GET Method

    //GET All
    @GetMapping("")
    public List<SimpleEventDTO> getAllEvents() {
        return eventServices.getAllEvents();
    }

    //GET by id
    @GetMapping("/{id}")
    public EventDetailDTO getEventById(
            @PathVariable Integer id
    ) {
        return eventServices.getEventById(id);
    }


//   POST Method

    //Create new event
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event createEvent(
            @RequestBody AddEventDetailDTO newEvent
    ) {
        return eventServices.save(newEvent);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ){
        eventServices.delete(id);
    }

    

}
