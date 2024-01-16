package imt.framework.back.imtframeworkback.integrationtests;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.core.errors.UserAuthException;
import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.data.repositories.UserRepository;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import imt.framework.back.imtframeworkback.presentation.rest.users.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserIT {
    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.13");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

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

            userController.createUser(CreateUserReq.builder()
                    .mail(mail).firstname(firstname).lastname(lastname).password(password)
                    .build());

            List<UserModel> users = userRepository.findAll();

            assertThat(users).hasSize(1);
            assertThat(users.get(0).getMail()).isEqualTo(mail);
            assertThat(users.get(0).getPassword()).isNotEqualTo(password);
        }

        @Test
        void createExistingUserShouldThrowUserAlreadyExistException() {
            String mail = "test.test@test.net";
            String firstname = "firstname";
            String lastname = "lastname";
            String password = "password";

            userRepository.save(UserModel.builder()
                    .mail(mail)
                    .password(password)
                    .build());

            assertThrows(
                    UserAlreadyExistException.class,
                    () -> userController.createUser(CreateUserReq.builder()
                            .mail(mail).firstname(firstname).lastname(lastname).password(password)
                            .build())
            );

            List<UserModel> users = userRepository.findAll();

            assertThat(users).hasSize(1);
        }
    }

    @Nested
    class Get {
        @Test
        void getUserShouldReturnUserRes() {
            String mail = "test@test.net";
            String password = "test";

            userController.createUser(CreateUserReq.builder()
                    .mail(mail).firstname("").lastname("").password(password)
                    .build());
            UserRes user = userController.authenticateUser(AuthUserReq.builder().mail(mail).password(password).build()).getBody();

            UserModel userModel = userRepository.findAll().get(0);
            assertThat(user.getUser().getId()).isEqualTo(userModel.getUserId());

        }

        @Test
        void getUserWrongPasswordShouldThrowUserAuthException() {
            String mail = "test@test.net";
            String password = "test";

            userController.createUser(CreateUserReq.builder()
                    .mail(mail).firstname("").lastname("").password("wrong")
                    .build());

            assertThrows(UserAuthException.class,
                    () -> userController.authenticateUser(AuthUserReq.builder().mail(mail).password(password).build())
            );
        }

        @Test
        void getNonExistentUserShouldThrowUserAuthException() {
            String mail = "test@test.net";
            String password = "test";

            assertThrows(
                    UserAuthException.class,
                    () -> userController.authenticateUser(AuthUserReq.builder().mail(mail).password(password).build())
            );
        }
    }
}
