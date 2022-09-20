package sit.int221.oasip.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/auth/login" )|| request.getServletPath().equals("/api/auth/refresh_token")){
            filterChain.doFilter(request,response);
        } else {
            System.out.println("Request => " + request.toString());
            String authorizationHeader = request.getHeader("access_token");
            System.out.println("access_token => " + authorizationHeader);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String access_token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("oasip-ssa1".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(access_token);

                    String email = decodedJWT.getSubject();
                    String[] role = decodedJWT.getClaim("role").asArray(String.class);
                    System.out.println("In authorization : " + role[0]);

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(role).forEach(r -> {
                        authorities.add(new SimpleGrantedAuthority(r));
                    });

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email , null , authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }catch (Exception e){

                    response.setStatus(401);

                    Map<String, String> errors = new HashMap<>();
                    errors.put("error" , e.getMessage());

                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), errors);
                }

            }else {
                System.out.println("Authorization Header = null");
                filterChain.doFilter(request, response);
            }
        }
    }
}
