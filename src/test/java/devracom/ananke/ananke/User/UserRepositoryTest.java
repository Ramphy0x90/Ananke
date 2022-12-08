package devracom.ananke.ananke.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * Test new user creation and saving in DB
     */
    @Test
    @DisplayName("User creation and saving on DB")
    public void saveUser() {
        User user = new User(
                "Username",
                "User surname",
                "user@mail.com",
                "Password",
                1,
                new ArrayList<>()
        );

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    /**
     * Test search user by given email
     */
    @Test
    @DisplayName("User search by email")
    public void findUserByEmail() {
        String[] users = {"Ananke", "Zeus", "Hermes"};

        for(String user: users) {
            userRepository.save(
                    new User(
                            user,
                            "Surname " + user,
                            user + "@mail.com",
                            "Password",
                            1,
                            new ArrayList<>()
                    )
            );
        }

        User searchUser = userRepository.findUserByEmail("Zeus@mail.com").orElseThrow();
        Assertions.assertNotNull(searchUser);
        Assertions.assertEquals("Zeus@mail.com", searchUser.getEmail());
    }
}