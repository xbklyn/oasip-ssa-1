package sit.int221.oasip.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.security.TokenRequest;
import sit.int221.oasip.dtos.security.TokenResponse;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.services.JwtService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder argon2;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder argon2, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.argon2 = argon2;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/match")
    public ResponseEntity match(@RequestBody TokenRequest information, HttpServletRequest req) {

        // Check existed email
        if (userRepository.findByUserEmail(information.getEmail()).isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorAdvice().getResponseEntity("userEmail", "This email does not existed.", req));
        }
        //Check matched password
        if (!argon2.matches(information.getRawPassword(), userRepository.findByUserEmail(information.getEmail()).get(0).getPassword())) {
            return ResponseEntity.status(401).body(new ErrorAdvice().getResponseEntity("password", "Password isn't matched, please try again.", req));
        }
        return ResponseEntity.status(200).body("matched!");
    }

    @GetMapping("/refresh_token")
    public ResponseEntity refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("refresh_token");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("oasip-ssa1".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String email = decodedJWT.getSubject();
                User user = userRepository.findByUserEmail(email).get(0);
                String role = user.getRole().getRoleName();

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));

                String access_token = JWT.create()
                        .withSubject(user.getUserEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("role", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                return ResponseEntity.status(200).body(new TokenResponse(access_token, refresh_token));
            }catch (Exception e){

                Map<String, String> errors = new HashMap<>();
                errors.put("error" , e.getMessage());
                return ResponseEntity.status(401).body(errors);
            }

        }else {
            return ResponseEntity.status(401).body("Refresh token is expired!");
        }
    }
}
