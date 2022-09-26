package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sit.int221.oasip.dtos.event.SimpleEventDTO;
import sit.int221.oasip.dtos.user.AccountDTO;
import sit.int221.oasip.entities.Event;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.repositories.EventCategoryRepository;
import sit.int221.oasip.repositories.EventRepository;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.utils.ListMapper;

import java.util.List;

@Transactional
@Service
public class HomeService {

    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final UserRepository userRepository;
    private final ListMapper listMapper;
    private final ModelMapper modelMapper;

    public HomeService(EventRepository eventRepository, EventCategoryRepository eventCategoryRepository, UserRepository userRepository, ListMapper listMapper, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.userRepository = userRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
    }

    public List<SimpleEventDTO> getAllEvent(){
        List<Event> events = eventRepository.findAll();
        return listMapper.mapList(events , SimpleEventDTO.class , modelMapper);
    }

    public List<Eventcategory> getAllCategory() {
        return eventCategoryRepository.findAll();
    }

    public ResponseEntity getByToken(Authentication auth) {
            User user = userRepository.findByUserEmail(String.valueOf(auth.getPrincipal())).get(0);
            return ResponseEntity.status(200).body(modelMapper.map(user, AccountDTO.class));
    }
}
