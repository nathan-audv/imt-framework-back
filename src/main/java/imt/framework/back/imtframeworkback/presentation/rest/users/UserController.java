package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import imt.framework.back.imtframeworkback.domain.usecases.users.AuthenticateUserUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.users.CreateUserUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.users.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserResources {
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Override
    public void createUser(CreateUserReq createUserReq) {
        createUserUseCase.command(createUserReq);
    }

    @Override
    public UserRes authenticateUser(AuthUserReq authUserReq) {
        return authenticateUserUseCase.command(authUserReq);
    }

    @Override
    public User updateUser(Integer userId, String firstname, String lastname, String password) {
        return null;
    }
}
