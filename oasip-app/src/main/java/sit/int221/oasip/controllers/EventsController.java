package sit.int221.oasip.controllers;

import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventRepository erepo;

    public EventsController(EventRepository erepo) {
        this.erepo = erepo;
    }

    @GetMapping("")
    public List<Event> getAllEvents() {
        return erepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Event createEvent(
            @RequestBody Event event
    ) {
        return erepo.saveAndFlush(event);
    }

    @GetMapping("/{id}")
    public Event getEventById(
        @PathVariable Integer id
    ) {
        return erepo.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
    
    @GetMapping("/category/{categoryId}")
    public List<Event> getEventByCategory(
            @PathVariable Integer categoryId
    ){
        return  erepo.findAllByEventCategoryId(categoryId);
    }
}
