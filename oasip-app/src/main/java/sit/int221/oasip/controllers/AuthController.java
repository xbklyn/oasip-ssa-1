package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.security.JwtReq;
import sit.int221.oasip.dtos.security.JwtRes;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.services.JwtService;
import sit.int221.oasip.services.UserService;
import sit.int221.oasip.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authenticate")
public class AuthController {

    private final UserRepository userRepository;
    private final Argon2PasswordEncoder argon2;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public AuthController(UserRepository userRepository, Argon2PasswordEncoder argon2, JwtService jwtService, AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.argon2 = argon2;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/match")
    public ResponseEntity match(@RequestBody JwtReq information , HttpServletRequest req){

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

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody JwtReq jwtReq) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtReq.getEmail(),
                            jwtReq.getRawPassword()
                    )
            );
        } catch (BadCredentialsException e ) {
            throw new Exception("INVALID CREDENTIALS." , e );
        }

        final UserDetails userDetails = jwtService.loadUserByUsername(jwtReq.getEmail());

        final String token = jwtUtils.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.status(200).body(new JwtRes(token));
    }
}
