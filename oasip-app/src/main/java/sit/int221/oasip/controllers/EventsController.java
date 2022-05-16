package sit.int221.oasip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

        import sit.int221.oasip.dtos.EventDetailDTO;
import sit.int221.oasip.dtos.PostEventDTO;
import sit.int221.oasip.dtos.PutEventDTO;
import sit.int221.oasip.dtos.SimpleEventDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.services.EventServices;

        import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
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

//    Create new event
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event createEvent(
            @RequestBody PostEventDTO newEvent
    ) {
        return eventServices.save(newEvent);
    }

//  DELETE Method

//  Delete existing event
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ){
        eventServices.delete(id);
    }

//         PUT Method
//        //Update Event
        @ResponseStatus(HttpStatus.OK)
        @PutMapping("/{id}")
        public Event edit(
                @RequestBody PutEventDTO editEventDTO,
                @PathVariable Integer id
                ){
            return eventServices.update(id , editEventDTO);
        }



}
