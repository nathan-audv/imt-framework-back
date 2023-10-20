package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

public class AuthenticateUserUseCaseTest {
    @InjectMocks
    private AuthenticateUserUseCase authenticateUserUseCase;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticateUserShouldReturnThem() {
        String mail = "test@test.net";
        String password = "test";
        User user = User.builder()
                .id(0)
                .mail(mail)
                .password(password)
                .build();

        AuthUserReq request = AuthUserReq.builder()
                .mail(mail)
                .password(password)
                .build();

        Mockito.when(userService.findByMail(mail)).thenReturn(Optional.of(user));

        UserRes res = authenticateUserUseCase.command(request);

        Assertions.assertThat(res.getUser()).isEqualTo(user);
    }
}
