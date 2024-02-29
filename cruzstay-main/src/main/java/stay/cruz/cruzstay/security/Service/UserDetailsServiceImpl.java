package stay.cruz.cruzstay.security.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stay.cruz.cruzstay.models.user.User;
import stay.cruz.cruzstay.security.model.AuthUser;
import stay.cruz.cruzstay.services.user.UserService;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService USerService;

    public UserDetailsServiceImpl(UserService uSerService) {
        USerService = uSerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.USerService.findUserByUsername(username);
        if (user.isPresent()) {
            return AuthUser.build(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
