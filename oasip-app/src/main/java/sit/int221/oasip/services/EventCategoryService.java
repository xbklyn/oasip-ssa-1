package sit.int221.oasip.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.repositories.EventCategoryRepository;

import java.util.List;

@Service
public class EventCategoryService {
    private final EventCategoryRepository eventCategoryRepository;

    public EventCategoryService(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    public List<Eventcategory> getAllCategory() {
        return eventCategoryRepository.findAll();
    }

    public Eventcategory getCategoryById(Integer id){
        return eventCategoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id" + id + " does not exist!" ));
    }
}
