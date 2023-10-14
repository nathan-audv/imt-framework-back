package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserWrongPasswordException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.UserServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.GetUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserUseCase implements UseCase<GetUserReq, User> {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User command(GetUserReq getUserReq) {
        Optional<User> user = userService.findByMail(getUserReq.getMail());
        if (user.isEmpty()) throw new UserNotFoundException(getUserReq.getMail());
        User existing = user.get();
        if (!passwordEncoder.matches(getUserReq.getPassword(), existing.getPassword())) {
            throw new UserWrongPasswordException(getUserReq.getMail());
        }
        return existing;
    }
}
