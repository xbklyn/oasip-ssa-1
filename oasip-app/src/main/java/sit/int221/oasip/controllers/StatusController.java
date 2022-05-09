package sit.int221.oasip.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasip.entities.Status;
import sit.int221.oasip.services.StatusService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ssa1/api/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("")
    public List<Status> getAllStatus() {
        return statusService.getAllStatus();
    }
}
