package sit.int221.oasip.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.PutCategoryDTO;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.EventCategoryRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EventCategoryService {
    private final EventCategoryRepository eventCategoryRepository;
    private final ErrorAdvice errorAdvice;

    public EventCategoryService(EventCategoryRepository eventCategoryRepository, ErrorAdvice errorAdvice) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.errorAdvice = errorAdvice;
    }

    // GET
    public List<Eventcategory> getAllCategory() {
        return eventCategoryRepository.findAll();
    }

    public Eventcategory getCategoryById(Integer id){
        return eventCategoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id" + id + " does not exist!" ));
    }

    // PUT
    public ResponseEntity edit(Integer id , PutCategoryDTO categoryDTO , HttpServletRequest req){

        //Check if name is not the same
        if(!eventCategoryRepository.checkUniqueName(categoryDTO.getEventCategoryName()).isEmpty())
            return ResponseEntity.status(400).body(errorAdvice.getResponseEntity("categoryName" , "Name is not unique", req));

        Eventcategory category = eventCategoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        //Set details
        category.setEventCategoryName(categoryDTO.getEventCategoryName());
        category.setEventCategoryDuration(categoryDTO.getEventCategoryDuration());
        return ResponseEntity.status(200).body(eventCategoryRepository.saveAndFlush(category));
    }
}
