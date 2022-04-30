package sit.int221.oasip.controllers;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
>>>>>>> parent of a0a1acc ([Test] Kuy 101 Not Found)
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;

import java.util.List;

<<<<<<< HEAD
@CrossOrigin("*")
=======
@CrossOrigin(origins = "http://10.4.56.107/")
>>>>>>> parent of a0a1acc ([Test] Kuy 101 Not Found)
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

    @GetMapping("/category/{categoryId}")
    public List<Event> getEventByCategory(
            @PathVariable Integer categoryId
    ){
        return  erepo.findAllByEventCategoryId(categoryId);
    }
}
