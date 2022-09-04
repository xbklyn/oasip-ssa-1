package sit.int221.oasip.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sit.int221.oasip.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class JwtService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(!userRepository.findByUserEmail(email).isEmpty()) {
            return new org.springframework.security.core.userdetails.User(
                    userRepository.findByUserEmail(email).get(0).getUserEmail()
                    , userRepository.findByUserEmail(email).get(0).getPassword(),new ArrayList<>());
        }

        throw new UsernameNotFoundException("This email does not existed!");
    }
}
