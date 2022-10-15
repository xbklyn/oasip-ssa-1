package sit.int221.oasip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import sit.int221.oasip.dtos.event.DetailEventDTO;
import sit.int221.oasip.dtos.event.PostEventDTO;
import sit.int221.oasip.dtos.event.PutEventDTO;
import sit.int221.oasip.dtos.event.SimpleEventDTO;
import sit.int221.oasip.dtos.time.TimeDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.services.EventServices;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final UserRepository userRepository;
    private final EventServices eventServices;

    public EventsController(EventRepository erepo, UserRepository userRepository, EventServices eventServices) {
        this.userRepository = userRepository;
        this.eventServices = eventServices;
    }

//  GET Method

    //GET All
    @GetMapping("")
    public List<SimpleEventDTO> getAllEvents(Authentication auth) {
        return eventServices.getAllEvents(auth);
    }

    //GET by id
    @GetMapping("/{id}")
    public ResponseEntity getEventById(
            @PathVariable Integer id,
            Authentication auth
    ) {
        return eventServices.getEventById(id , auth);
    }

    @GetMapping("/{categoryId}/{date}")
    public List<TimeDTO> getEventByCategoryIdAndDate(
            @PathVariable Integer categoryId,
            @PathVariable String date
    ){
        System.out.println(categoryId + " " + date);
        return eventServices.getEventByCatIdAndDate(categoryId, date);
    }

//   POST Method

//    Create new event
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity createEvent(
           @Valid @RequestPart("body") PostEventDTO newEvent,
           @RequestPart("file") MultipartFile file,
           HttpServletRequest req,
           Authentication auth
    ) throws MethodArgumentNotValidException, ParseException {
        return eventServices.save(newEvent,file , req , auth);
    }

//  DELETE Method

//  Delete existing event
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable Integer id,
            Authentication auth
    ){
        return eventServices.delete(id, auth);
    }

//         PUT Method
//        //Update Event
        @ResponseStatus(HttpStatus.OK)
        @PutMapping("/{id}")
        public ResponseEntity edit(
                @Valid @RequestBody PutEventDTO editEventDTO,
                @PathVariable Integer id,
                Authentication auth
                ){
            return eventServices.update(id , editEventDTO, auth);
        }



}
