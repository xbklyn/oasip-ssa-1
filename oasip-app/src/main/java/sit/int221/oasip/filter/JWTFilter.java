package sit.int221.oasip.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sit.int221.oasip.services.JwtService;
import sit.int221.oasip.utils.JWTUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private JwtService jwtService;

    public JWTFilter(JWTUtils jwtUtils, JwtService jwtService) {
        this.jwtUtils = jwtUtils;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if(null != authorization && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            email = jwtUtils.getUsernameFromToken(token);
        }
        if(null != email && SecurityContextHolder.getContext().getAuthentication() == null ) {
            UserDetails userDetails = jwtService.loadUserByUsername(email);

            if(jwtUtils.validateToken(token,userDetails));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
