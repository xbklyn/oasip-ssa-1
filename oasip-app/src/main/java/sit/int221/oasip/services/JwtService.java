package sit.int221.oasip.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(!userRepository.findByUserEmail(email).isEmpty()) {
            User user = userRepository.findByUserEmail(email).get(0);
            Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
            authority.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
            return new org.springframework.security.core.userdetails.User(
                    user.getUserEmail(), user.getPassword() ,authority);
        }

        throw new UsernameNotFoundException("This email does not existed!");
    }
}
