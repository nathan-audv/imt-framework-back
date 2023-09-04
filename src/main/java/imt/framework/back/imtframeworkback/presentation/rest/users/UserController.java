package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.requests.UserReq;
import imt.framework.back.imtframeworkback.domain.usecases.users.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserResources {
    private final CreateUserUseCase createUserUseCase;

    @Override
    public void createUser(String mail, String firstname, String lastname, String password) {
        createUserUseCase.command(UserReq.builder()
                .mail(mail).firstname(firstname).lastname(lastname).password(password)
                .build());
    }
}
