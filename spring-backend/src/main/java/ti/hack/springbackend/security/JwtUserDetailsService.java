package ti.hack.springbackend.security;

import ti.hack.springbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private static final String EMAIL_NOT_FOUND_MSG = "User with email %s not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND_MSG, s)));
    }
}