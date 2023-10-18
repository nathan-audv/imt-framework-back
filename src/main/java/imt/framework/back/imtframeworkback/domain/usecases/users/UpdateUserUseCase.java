package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.models.User.UserBuilder;
import imt.framework.back.imtframeworkback.domain.requests.UpdateUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserUseCase implements UseCase<UpdateUserReq, User> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User command(UpdateUserReq request) {
        UserBuilder user = userService.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException(request.getUserId().toString())).toBuilder();
        if (request.getFirstname() != null) user.firstname(request.getFirstname());
        if (request.getLastname() != null) user.lastname(request.getLastname());
        if (request.getPassword() != null) user.password(passwordEncoder.encode(request.getPassword()));
        return userService.save(user.build());
    }
}
