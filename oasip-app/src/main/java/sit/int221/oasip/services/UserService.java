package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.user.*;
import sit.int221.oasip.entities.Role;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.errors.ApiError;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.RoleRepository;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.utils.ListMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ListMapper listMapper;
    private final ModelMapper modelMapper;
    private final ErrorAdvice errorAdvice;
    private final Argon2PasswordEncoder argon2;
    public UserService(RoleRepository roleRepository, UserRepository userRepository, ListMapper listMapper, ModelMapper modelMapper, ErrorAdvice errorAdvice, Argon2PasswordEncoder argon2) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
        this.errorAdvice = errorAdvice;
        this.argon2 = argon2;
    }

    //GET all
    public List<SimpleUserDTO> getAll(){
        return listMapper.mapList(userRepository.findAll(Sort.by("userName").ascending()) , SimpleUserDTO.class , modelMapper);
    }

    //GET by ID
    public ResponseEntity getById(Integer id , Authentication auth) {
        //Check if role is admin
        String role = String.valueOf(auth.getAuthorities().toArray()[0]);
        String email = auth.getPrincipal().toString();
        if(role.equals("admin")){
            User user = userRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " does not exist!")
            );
            return ResponseEntity.status(200).body(modelMapper.map(user, DetailUserDTO.class));
        }
        //Check if email not equals to getting user
        if(!email.equals(userRepository.findById(id).get().getUserEmail())){
            Map<String , String > error = new HashMap<>();
            error.put("Unauthorized" , "Access Denied.");
            return ResponseEntity.status(403).body(error);
        }
        return ResponseEntity.status(200).body(modelMapper.map(userRepository.findById(id).get(), DetailUserDTO.class));
    }

    //GET by role
    public List<SimpleUserDTO> getByRole(String role_name){
        Role role = roleRepository.findByRoleName(role_name);
        List<User> users = userRepository.findByRole(role);
        return listMapper.mapList(users ,SimpleUserDTO.class, modelMapper);
    }

    //Get By Create
    public List<SimpleUserDTO> getByCreate(String date){
        return listMapper.mapList(userRepository.findByCreatedOn(date), SimpleUserDTO.class, modelMapper);
    }

    //GET by Update
    public List<SimpleUserDTO> getByUpdate(String date){
        return listMapper.mapList(userRepository.findByUpdatedOn(date), SimpleUserDTO.class, modelMapper);
    }

    //POST
    public ResponseEntity create(PostUserDTO user , HttpServletRequest req){
        //Validate name and email
        Map<String , String > errors = validate(user.getUserName() , user.getUserEmail());

        //Check role
        Integer roleId = user.getRole().isEmpty() || user.getRole().equals("")
                ? roleRepository.findByRoleName("student").getId()
                : roleRepository.findByRoleName(user.getRole()) == null
                    ? 0
                    : roleRepository.findByRoleName(user.getRole()).getId();
        if(roleId == 0 ) errors.put("role" , "must be student, lecturer or admin");

        //Check confirm password
        if(!user.getPassword().matches(user.getConfirmPassword()))
            errors.put("password" , "Password does not match, please confirm your password again.");

        return errors.isEmpty()
                ? ResponseEntity.status(201).body(userRepository.create(user.getUserName().trim(), user.getUserEmail(), argon2.encode(user.getPassword()), roleId))
                : ResponseEntity.status(400).body(errorAdvice.getAllErrors(errors,req)) ;
    }

    //PUT
    public ResponseEntity update(Integer id, PutUserDTO putUser , HttpServletRequest req){
        Map<String, String> errors = new HashMap<>();
        AtomicBoolean edited = new AtomicBoolean(false);
        //Get User
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " does not exist."));

        //Check same name
        if(!user.getUserName().matches(putUser.getUserName())){
            if(userRepository.findByUserName(putUser.getUserName().trim()).size() > 0) {
                errors.put("userName", "must be unique.");
            }
            else{
                user.setUserName(putUser.getUserName());
                edited.set(true);
            }
        }

        //Check email
        if(!user.getUserEmail().matches(putUser.getUserEmail())){
            //Check regex
            // String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            if(!putUser.getUserEmail().trim().matches(regex)) {
                errors.put("userEmail", "must be an well-formed email");
            }

            //Check Unique email
            else if(userRepository.findByUserEmail(putUser.getUserEmail().trim()).size() > 0) {
                errors.put("userEmail", "this email is already used");
            }
            else {
                user.setUserEmail(putUser.getUserEmail());
                edited.set(true);
            }

        }
        //Check role
        if(!user.getRole().getRoleName().matches(putUser.getRole())){
        Role role = putUser.getRole().isEmpty() || putUser.getRole().equals("")
                ? roleRepository.findByRoleName("student")
                : roleRepository.findByRoleName(putUser.getRole());
        user.setRole(role);
        edited.set(true);
        }
        
        return errors.isEmpty()
                ? edited.get()
                    ? ResponseEntity.status(200).body(userRepository.update(id , user.getUserName() , user.getUserEmail() , user.getRole().getId()))
                    : ResponseEntity.status(200).body("We did not send a request to DB!")
                : ResponseEntity.status(400).body(errorAdvice.getAllErrors(errors,req));
    }

    //DELETE
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public Map<String, String> validate(String name, String email){
        Map<String,String> errors = new HashMap<>();
        //Check Unique name
        if(!userRepository.findByUserName(name.trim()).isEmpty())
            errors.put("userName" , "must be unique.");

        //Check emails
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(!email.trim().matches(regex))
            errors.put("userEmail" , "must be an well-formed email");

        //Check Unique email
        if(!userRepository.findByUserEmail(email.trim()).isEmpty())
            errors.put("userEmail", "this email is already used");

        return errors;
    }

}
