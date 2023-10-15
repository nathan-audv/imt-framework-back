package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserWrongPasswordException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.repositories.RoleRepository;
import imt.framework.back.imtframeworkback.data.repositories.UserRepository;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.GetUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticateUserUseCase implements UseCase<GetUserReq, User> {
    private UserService userService;
    private RoleService roleService;
    private TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User command(GetUserReq getUserReq) {
        User user = userService.findByMail(getUserReq.getMail())
                .orElseThrow(() -> new UserNotFoundException(getUserReq.getMail()));

        if (!passwordEncoder.matches(getUserReq.getPassword(), user.getPassword())) {
            throw new UserWrongPasswordException(getUserReq.getMail());
        }

        return user;
    }
}
