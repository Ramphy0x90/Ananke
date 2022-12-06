package devracom.ananke.ananke;

import devracom.ananke.ananke.User.UserController;
import devracom.ananke.ananke.User.UserControllerAdvice;
import devracom.ananke.ananke.User.UserRepository;
import devracom.ananke.ananke.User.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnankeApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserController userController;
	@Autowired
	private UserControllerAdvice userControllerAdvice;

	@Test
	void contextLoads() {
		// Test user resources
		Assertions.assertThat(userRepository).isNotNull();
		Assertions.assertThat(userService).isNotNull();
		Assertions.assertThat(userController).isNotNull();
		Assertions.assertThat(userControllerAdvice).isNotNull();
	}

}
