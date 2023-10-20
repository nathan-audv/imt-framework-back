package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.UpdateFavoritesReq;
import imt.framework.back.imtframeworkback.domain.requests.UpdateUserReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class UpdateUserCaseTest {
    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void notValidUserShouldThrowUserNotValidException(){
        Integer userId = 0;
        UpdateUserReq request = UpdateUserReq.builder()
                .userId(userId)
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(false);

            assertThrows(UserNotValidException.class, () -> updateUserUseCase.command(request));
        }
    }

    @Test
    public void updateNotExistingUserShouldThrowUserNotFoundException(){
        Integer userId = 0;
        UpdateUserReq request = UpdateUserReq.builder()
                .userId(userId)
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Mockito.when(userService.findById(userId)).thenReturn(Optional.empty());

            assertThrows(UserNotFoundException.class, () -> updateUserUseCase.command(request));
        }
    }

    /*@Test
    public void updateUserShouldReturnNewOne(){
        Integer userId = 0;
        User user = User.builder()
                .mail("test@test.net")
                .firstname("f")
                .lastname("l")
                .password("p")
                .build();

        UpdateUserReq request = UpdateUserReq.builder()
                .userId(userId)
                .firstname("first")
                .lastname("last")
                .password("pass")
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Mockito.when(userService.findById(userId)).thenReturn(Optional.of(user));
            Mockito.when(userService.save(any())).thenReturn(user);

            User res = updateUserUseCase.command(request);

            Assertions.assertThat(res.getId()).isEqualTo(user.getId());
            Assertions.assertThat(res.getMail()).isEqualTo(user.getMail());
            Assertions.assertThat(res.getFirstname()).isNotEqualTo(user.getFirstname());
            Assertions.assertThat(res.getLastname()).isNotEqualTo(user.getLastname());
            Assertions.assertThat(res.getPassword()).isNotEqualTo(user.getPassword());
        }
    }
    problems with service save returning
    */
}
