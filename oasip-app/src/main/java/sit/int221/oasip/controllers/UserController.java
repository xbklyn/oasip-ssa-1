package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.user.PostUserDTO;
import sit.int221.oasip.dtos.user.PutUserDTO;
import sit.int221.oasip.dtos.user.SimpleUserDTO;
import sit.int221.oasip.dtos.user.DetailUserDTO;
import sit.int221.oasip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public List<SimpleUserDTO> getAllUser() {
        return userService.getAll();
    }

    //GET by ID
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id , Authentication auth)
    {return userService.getById(id, auth);}

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


    //POST
    @PostMapping("")
    public ResponseEntity createUser(
            @Valid @RequestBody PostUserDTO user,
            HttpServletRequest req
    ) throws MethodArgumentNotValidException {
        return userService.create(user, req);
    }

//    //PUT
    @PutMapping("/{id}")
    public ResponseEntity updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody PutUserDTO user,
            HttpServletRequest req
    ) throws MethodArgumentNotValidException{
        return userService.update(id, user, req);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ){
        userService.delete(id);
    }

}
