package sit.int221.oasip.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.PutCategoryDTO;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.errors.ApiError;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.EventCategoryRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ApiError hasError = validate(categoryDTO, req);
        if (!hasError.getDetails().isEmpty())
            return ResponseEntity.status(400).body(hasError);

        Eventcategory category = eventCategoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        //Set details
        category.setEventCategoryName(categoryDTO.getEventCategoryName());
        category.setEventCategoryDuration(categoryDTO.getEventCategoryDuration());
        return ResponseEntity.status(200).body(eventCategoryRepository.saveAndFlush(category));
    }

    private ApiError validate(PutCategoryDTO categoryDTO, HttpServletRequest req) {
        ApiError error = new ApiError(400 , "Validation Failed" , req.getServletPath());

        Map<String, String> details = new HashMap<>();
        //Check if name is not the same
        if(!eventCategoryRepository.checkUniqueName(categoryDTO.getEventCategoryName()).isEmpty())
            details.put("eventCategoryName" , "Name is not unique");

        if( categoryDTO.getEventCategoryDuration() <= 0 || categoryDTO.getEventCategoryDuration() > 480)
            details.put("eventCategoryDuration" , "Duration must be between 1 and 480");

        error.setDetails(details);
        return error;
    }
}
