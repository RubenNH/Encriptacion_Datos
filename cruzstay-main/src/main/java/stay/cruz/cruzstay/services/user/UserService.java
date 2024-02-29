package stay.cruz.cruzstay.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stay.cruz.cruzstay.models.user.User;
import stay.cruz.cruzstay.models.user.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }
}
