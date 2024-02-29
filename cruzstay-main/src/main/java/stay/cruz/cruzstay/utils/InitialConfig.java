package stay.cruz.cruzstay.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import stay.cruz.cruzstay.models.role.Role;
import stay.cruz.cruzstay.models.role.RoleRepository;
import stay.cruz.cruzstay.models.user.User;
import stay.cruz.cruzstay.models.user.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
        Role adminRole = getOrSaveRole(new Role("SUPER_ADMIN_ROLE"));
        getOrSaveRole(new Role("ADMIN_ROLE"));
        getOrSaveRole(new Role("USER_ROLE"));
        getOrSaveRole(new Role("CLIENT_ROLE"));
        getOrSaveRole(new Role("SELLER_CLIENT_ROLE"));
        User user = getOrSaveUser(
                new User("admin", encoder.encode("admin"))
        );
        System.out.println(user.getId() + " " + adminRole.getId());
        saveUserRoles(user.getId(), adminRole.getId());
    }

    @Transactional
    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        return foundRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }

    @Transactional
    public User getOrSaveUser(User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser.orElseGet(() -> userRepository.saveAndFlush(user));
    }
    @Transactional
    public void saveUserRoles(Long id, Long roleId) {
        Long userRoleId = userRepository.getIdUserRoles(id, roleId);
        if (userRoleId == null)
            userRepository.saveUserRole(id, roleId);
    }

}
