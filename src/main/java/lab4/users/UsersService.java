package lab4.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    UsersService(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(String username, String password) {
        if (repository.existsById(username)) {
            return false;
        }

        repository.save(new UserEntity(username, passwordEncoder.encode(password)));
        return true;
    }

    public UserEntity find(String username) {
        return repository.findById(username).orElse(null);
    }
}
