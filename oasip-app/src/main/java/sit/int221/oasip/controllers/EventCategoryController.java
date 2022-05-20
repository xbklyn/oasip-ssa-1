package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.PutCategoryDTO;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.services.EventCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryRepository) {
        this.eventCategoryService = eventCategoryRepository;
    }

    // GET Method

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

    // PUT Method

    // Edit Existing category
    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable Integer id,
            @Valid @RequestBody PutCategoryDTO catDetails,
            HttpServletRequest req
            ){
        return eventCategoryService.edit(id, catDetails , req);
    }
}
