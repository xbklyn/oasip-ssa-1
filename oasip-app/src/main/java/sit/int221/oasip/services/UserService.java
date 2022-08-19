package sit.int221.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasip.dtos.SimpleUserDTO;
import sit.int221.oasip.dtos.UserDetailDTO;
import sit.int221.oasip.entities.Role;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.repositories.RoleRepository;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.utils.ListMapper;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ListMapper listMapper;
    private final ModelMapper modelMapper;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, ListMapper listMapper, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
    }

    //GET all
    public List<SimpleUserDTO> getAll(){
        return listMapper.mapList(userRepository.findAll(Sort.by("userName").ascending()) , SimpleUserDTO.class , modelMapper);
    }

    //GET by ID
    public UserDetailDTO getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " does not exist!")
        );
        return modelMapper.map(user, UserDetailDTO.class);
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
}
