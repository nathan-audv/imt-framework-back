package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.core.utils.Constants;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Role;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class CreateUserUseCaseTest {
    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAlreadyExistingUserShouldThrowsUserAlreadyExistException() {
        String mail = "test@test.net";
        User user = User.builder().mail(mail).build();

        CreateUserReq request = CreateUserReq.builder().mail(mail).firstname("first").lastname("last").password("pass").build();

        Mockito.when(userService.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class, () -> createUserUseCase.command(request));

        Mockito.verify(userService, Mockito.times(0)).saveWithoutReturn(any());
    }

    @Test
    public void createUserShouldCallSaveWithoutReturn() {
        String mail = "test@test.net";
        Role role = Role.builder().authority(Constants.USER_ROLE).build();

        CreateUserReq request = CreateUserReq.builder().mail(mail).firstname("first").lastname("last").password("pass").build();

        Mockito.when(userService.findByMail(mail)).thenReturn(Optional.empty());
        Mockito.when(roleService.findByRole(Constants.USER_ROLE)).thenReturn(Optional.of(role));

        createUserUseCase.command(request);

        Mockito.verify(userService, Mockito.times(1)).saveWithoutReturn(any());
    }
}
