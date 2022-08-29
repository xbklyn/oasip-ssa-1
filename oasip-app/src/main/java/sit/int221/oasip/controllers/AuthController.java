package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasip.dtos.AuthDTO;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/match")
public class AuthController {

    private final UserRepository userRepository;
    private final Argon2PasswordEncoder argon2;

    public AuthController(UserRepository userRepository, Argon2PasswordEncoder argon2) {
        this.userRepository = userRepository;
        this.argon2 = argon2;
    }

    @PostMapping("")
    public ResponseEntity match(@RequestBody AuthDTO information , HttpServletRequest req){
        
        // Check existed email
        if(userRepository.findByUserEmail(information.getEmail()).isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorAdvice().getResponseEntity("userEmail", "This email does not existed.", req));
        }
        //Check matched password
        if(!argon2.matches(information.getRawPassword(), userRepository.findByUserEmail(information.getEmail()).get(0).getPassword())) {
            return ResponseEntity.status(401).body(new ErrorAdvice().getResponseEntity("password", "Password isn't matched, please try again.", req));
        }
        return ResponseEntity.status(200).body("matched!");
    }
}
