package devracom.ananke.ananke.User;

import devracom.ananke.ananke.User.dto.UserNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@WebMvcTest(UserController.class)
@WithMockUser(username="admin",roles="ADMIN")
class UserControllerTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        String[] users = {"Ananke", "Zeus", "Hermes"};

        for(String user: users) {
            User test = userRepository.save(
                new User(
                        user,
                        "Surname " + user,
                        user + "@mail.com",
                        "Password",
                        1,
                        new ArrayList<>()
                )
            );

            System.out.println(test);
        }

        System.out.println(userService.getUsers());
    }

    @Test
    void getUsers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/user/all");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(3, result.getResponse().getContentLength());
    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}