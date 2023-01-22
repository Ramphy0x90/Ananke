package devracom.ananke.ananke.User;

import devracom.ananke.ananke.User.dto.UserNew;
import devracom.ananke.ananke.User.dto.UserResponse;
import devracom.ananke.ananke.User.dto.UserUpdate;
import devracom.ananke.ananke.User.exceptions.UserAlreadyExistsException;
import devracom.ananke.ananke.User.exceptions.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get all users
     * @return List<User>
     */
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponse = new ArrayList<>();

        for(User user: users) {
            usersResponse.add(
                    new UserResponse(user.getId(),
                            user.getName(),
                            user.getSurname(),
                            user.getEmail(),
                            user.getStatus(),
                            user.getRoles()
                    )
            );
        }
        return usersResponse;
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
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("User with email: %s not found", email))
        );
    }

    /**
     * Create a new user with the given object
     * and return the new user
     * @param user user data
     * @return User
     */
    public User createUser(UserNew user) {
        Optional<User> findUser = userRepository.findUserByEmail(user.getEmail());

        if(findUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email: %s already exists", user.getEmail()));
        } else {
            User newUser = new User(
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),
                    1,
                    new ArrayList<>()
            );

            return userRepository.save(newUser);
        }
    }

    /**
     * Update data of a given user
     * @param id user id
     * @param userUpdate data to update
     * @return User
     */
    public User updateUser(Long id, UserUpdate userUpdate) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User with ID: %s not found", id))
        );

        user.setName(userUpdate.getName());
        user.setSurname(userUpdate.getSurname());
        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        user.setStatus(userUpdate.getStatus());

        return user;
    }

    /**
     * Delete an user by given id
     * @param id  user id
     */
    public void deleteUser(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User with ID: %s not found", id))
        );

        userRepository.deleteById(id);
    }
}
