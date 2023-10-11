package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.requests.GetUserReq;
import imt.framework.back.imtframeworkback.domain.usecases.users.CreateUserUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.users.AuthenticateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserResources {
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @Override
    public User createUser(String mail, String firstname, String lastname, String password) {
        return createUserUseCase.command(CreateUserReq.builder()
                .mail(mail).firstname(firstname).lastname(lastname).password(password)
                .build());
    }

    @Override
    public User authenticateUser(String mail, String password) {
        return authenticateUserUseCase.command(GetUserReq.builder().mail(mail).password(password).build());
    }
}
