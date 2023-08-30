package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.UserServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<UserReq, Void> {
    private final UserServiceImpl userService;

    @Override
    public Void command(UserReq userReq) {
        User user = User.fromReq(userReq);
        //TODO change balance
        userService.save(user);
        return null;
    }
}
