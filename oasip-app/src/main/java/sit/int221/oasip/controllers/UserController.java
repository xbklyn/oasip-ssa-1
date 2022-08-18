package sit.int221.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.SimpleUserDTO;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.services.UserService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET all
    @GetMapping("")
    public List<User> getAllUser() {
        return userService.getAll();
    }

    //GET by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id)
    {return userService.getById(id);}

    //GET by role
    @GetMapping("/role/{role}")
    public List<SimpleUserDTO> getUserByRole(@PathVariable String role)
    {return userService.getByRole(role);}

    //GET by Create On
    @GetMapping("/create/{date}")
    public List<SimpleUserDTO> getUserByCreatedOn(@PathVariable String date)
    {return userService.getByCreate(date);}

    //GET by Update On
    @GetMapping("/update/{date}")
    public List<SimpleUserDTO> getUserByUpdateOn(@PathVariable String date)
    {return userService.getByUpdate(date);}

}
