package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.category.PutCategoryDTO;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.services.EventCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryRepository) {
        this.eventCategoryService = eventCategoryRepository;
    }

    // GET Method

    // Get all category
    @GetMapping("")
    public List<Eventcategory> getAllCategory() {
        return eventCategoryService.getAllCategory();
    }

    // Get by id
    @GetMapping("/{categoryId}")
    public Eventcategory getCategoryById(
            @PathVariable Integer categoryId
    ){
        return eventCategoryService.getCategoryById(categoryId);
    }

    // PUT Method

    // Edit category
    @PutMapping("/{categoryId}")
    public ResponseEntity update(
            @PathVariable Integer categoryId,
            @Valid @RequestBody PutCategoryDTO catDetails,
            HttpServletRequest req
            ){
        return eventCategoryService.edit(categoryId, catDetails , req);
    }
}
