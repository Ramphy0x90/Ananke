package devracom.ananke.ananke.User;

import devracom.ananke.ananke.User.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users
     * @return List<User>
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by given id
     * @param id user id
     * @return User
     */
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User with ID: %s not found", id))
        );
    }

    /**
     * Get user by given email
     * @param email user email
     * @return User
     */
    public User getUser(String email) {
        Optional<User> findUser = userRepository.findUserByEmail(email);

        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("User with email: %s not found", email))
        );
    }
}
