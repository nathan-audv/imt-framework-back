package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.impl.UserServiceImpl;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<CreateUserReq, Void> {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Void command(CreateUserReq createUserReq) {
        User user = User.fromReq(createUserReq);
        Optional<User> existing = userService.findByMail(user.getMail());
        if(existing.isPresent()) {
            throw new UserAlreadyExistException(user.getMail());
        }
        String password = passwordEncoder.encode(user.getPassword());
        user = user.toBuilder().password(password).balance(200.0).build();
        userService.save(user);
        return null;
    }
}
