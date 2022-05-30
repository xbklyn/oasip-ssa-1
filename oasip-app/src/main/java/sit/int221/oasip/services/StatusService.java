package sit.int221.oasip.services;

import org.springframework.stereotype.Service;
import sit.int221.oasip.entities.Status;
import sit.int221.oasip.repositories.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }
}
