package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasip.dtos.event.SimpleEventDTO;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.services.HomeService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(EventRepository eventRepository, HomeService homeService) {

        this.homeService = homeService;
    }

    @GetMapping("/events")
    public List<SimpleEventDTO> getAll(){
        return homeService.getAllEvent();
    }

    @GetMapping("/category")
    public List<Eventcategory> getAllCategory() {
        return homeService.getAllCategory();
    }

    @GetMapping("/user")
    public ResponseEntity getUserbyToken(Authentication auth)
    {return homeService.getByToken(auth);}
}
