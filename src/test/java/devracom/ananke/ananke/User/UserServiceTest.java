package devracom.ananke.ananke.User;

import devracom.ananke.ananke.User.dto.UserNew;
import devracom.ananke.ananke.User.dto.UserUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    @DisplayName("Create new user")
    void createUser() {
        UserNew user = new UserNew(
                "Username",
                "User surname",
                "user@mail.com",
                "Password",
                1
        );
        User userCreated = userService.createUser(user);

        Assertions.assertNotNull(userCreated);
        Assertions.assertNotEquals(userCreated.getPassword(), user.getPassword());
    }

    @Test
    @DisplayName("Update user data")
    void updateUser() {
        UserNew user = new UserNew(
                "Username",
                "User surname",
                "user@mail.com",
                "Password",
                1
        );
        UserUpdate userUpdate = new UserUpdate(
                "NewUsername",
                user.getSurname(),
                "NewPassword",
                1
        );
        User userCreated = userService.createUser(user);
        User userUpdated = userService.updateUser(userCreated.getId(), userUpdate);

        Assertions.assertNotNull(userUpdated);
        Assertions.assertNotEquals(user.getName(), userUpdated.getName());
        Assertions.assertNotEquals(user.getPassword(), userUpdated.getPassword());
    }

    @Test
    void deleteUser() {
    }
}