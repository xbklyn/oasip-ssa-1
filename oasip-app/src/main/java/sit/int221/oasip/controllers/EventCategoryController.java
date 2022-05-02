package sit.int221.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.repositories.EventCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class EventCategoryController {

    private final EventCategoryRepository ecrepo;

    public EventCategoryController(EventCategoryRepository ecrepo) {
        this.ecrepo = ecrepo;
    }

    @GetMapping("")
    public List<Eventcategory> getAllCategory() {
        return ecrepo.findAll();
    }
}
