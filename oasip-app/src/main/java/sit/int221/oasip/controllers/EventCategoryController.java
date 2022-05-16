package sit.int221.oasip.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.services.EventCategoryService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryRepository) {
        this.eventCategoryService = eventCategoryRepository;
    }

    @GetMapping("")
    public List<Eventcategory> getAllCategory() {
        return eventCategoryService.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public Eventcategory getCategoryById(
            @PathVariable Integer categoryId
    ){
        return eventCategoryService.getCategoryById(categoryId);
    }
}
