package sit.int221.oasip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasip.dtos.security.TokenRequest;
import sit.int221.oasip.dtos.security.TokenResponse;
import sit.int221.oasip.errors.ErrorAdvice;
import sit.int221.oasip.repositories.UserRepository;
import sit.int221.oasip.services.JwtService;
import sit.int221.oasip.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
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

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody TokenRequest jwtReq) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtReq.getEmail(),
                            jwtReq.getRawPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS.", e);
        }

        final UserDetails userDetails = jwtService.loadUserByUsername(jwtReq.getEmail());

        final String access_token = jwtUtils.generateToken(userDetails);
        final String refresh_token = jwtUtils.generateRefreshToken(userDetails);
        System.out.println("access_token : " + access_token);
        System.out.println("refresh_token : " + refresh_token);

        return ResponseEntity.status(200).body(new TokenResponse(access_token, refresh_token));
    }

    @GetMapping("/refresh_token")
    public ResponseEntity refreshToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String refresh_token = null;
        String email = null;
        String  access_token = null;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            refresh_token = authorization.substring(7);
            email = jwtUtils.getUsernameFromToken(refresh_token);

        }
        if (null != email) {
            UserDetails userDetails = jwtService.loadUserByUsername(email);
            access_token = jwtUtils.generateToken(userDetails);

            if (jwtUtils.validateToken(refresh_token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }else {
            throw new RuntimeException("Refresh Token is missing.");
        }

        return ResponseEntity.status(400).body(new TokenResponse(access_token , refresh_token));
    }
}
