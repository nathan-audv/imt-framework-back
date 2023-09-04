package imt.framework.back.imtframeworkback.integrationtests;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.data.repositories.UserRepository;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.presentation.rest.users.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {
    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Nested
    class Create {
        @Test
        void createUserShouldReturnSavedUser() {
            String mail = "test.test@test.net";
            String firstname = "firstname";
            String lastname = "lastname";
            String password = "password";

            User user = userController.createUser(mail, firstname, lastname, password);

            UserModel userModel = userRepository.findAll().get(0);

            assertThat(user).isEqualTo(User.fromData(userModel));
        }

        @Test
        void createExistingUserShouldThrowUserAlreadyExistException() {
            String mail = "test.test@test.net";
            String firstname = "firstname";
            String lastname = "lastname";
            String password = "password";

            userController.createUser(mail, firstname, lastname, password);

            assertThrows(
                    UserAlreadyExistException.class,
                    () -> userController.createUser(mail, firstname, lastname, password)
            );

            List<UserModel> users = userRepository.findAll();

            assertThat(users).hasSize(1);
        }
    }
}
