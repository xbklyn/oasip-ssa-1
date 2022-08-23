package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.PostUserDTO;
import sit.int221.oasip.dtos.PutUserDTO;
import sit.int221.oasip.dtos.SimpleUserDTO;
import sit.int221.oasip.dtos.DetailUserDTO;
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

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ListMapper listMapper;
    private final ModelMapper modelMapper;
    private final ErrorAdvice errorAdvice;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, ListMapper listMapper, ModelMapper modelMapper, ErrorAdvice errorAdvice) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
        this.errorAdvice = errorAdvice;
    }

    //GET all
    public List<SimpleUserDTO> getAll(){
        return listMapper.mapList(userRepository.findAll(Sort.by("userName").ascending()) , SimpleUserDTO.class , modelMapper);
    }

    //GET by ID
    public DetailUserDTO getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " does not exist!")
        );
        return modelMapper.map(user, DetailUserDTO.class);
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
        Integer roleId = user.getRole().isEmpty()
                ? roleRepository.findByRoleName("student").getId()
                : roleRepository.findByRoleName(user.getRole()).getId();

        return errors.isEmpty()
                ? ResponseEntity.status(201).body(userRepository.create(user.getUserName().trim(), user.getUserEmail(), roleId))
                : ResponseEntity.status(400).body(errorAdvice.getAllErrors(errors,req)) ;
    }

    //PUT
    public ResponseEntity update(Integer id, PutUserDTO putUser , HttpServletRequest req){
        Map<String, String> errors = new HashMap<>();

        //Get User
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " does not exist."));

        //Check same name
        if(!user.getUserName().matches(putUser.getUserName())){
            if(userRepository.findByUserName(putUser.getUserName().trim()).size() > 1) {
                errors.put("userName", "must be unique.");
            }
            user.setUserName(putUser.getUserName());
        }

        //Check email
        if(!user.getUserEmail().matches(putUser.getUserEmail())){
            //Check regex
            String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            if(!putUser.getUserEmail().trim().matches(regex)) {
                errors.put("userEmail", "must be an well-formed email");
            }

            //Check Unique email
            if(userRepository.findByUserEmail(putUser.getUserEmail().trim()).size() > 1) {
                errors.put("userEmail", "this email is already used");
            }

            user.setUserEmail(putUser.getUserEmail());
        }
        //Check role
        Integer roleId = putUser.getRole().isEmpty()
                ? roleRepository.findByRoleName("student").getId()
                : roleRepository.findByRoleName(putUser.getRole()).getId();

        return errors.isEmpty()
                ? ResponseEntity.status(200).body(userRepository.saveAndFlush(user))
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
