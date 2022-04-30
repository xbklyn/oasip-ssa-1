package sit.int221.oasip.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventsController {

    private final EventRepository erepo;

    public EventsController(EventRepository erepo) {
        this.erepo = erepo;
    }

    @GetMapping("")
    public List<Event> getAllEvents() {
        return erepo.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Event> getEventByCategory(
            @PathVariable Integer categoryId
    ){
        return  erepo.findAllByEventCategoryId(categoryId);
    }
}
