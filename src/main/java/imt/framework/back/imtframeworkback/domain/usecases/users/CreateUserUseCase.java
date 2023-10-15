package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserAlreadyExistException;
import imt.framework.back.imtframeworkback.core.utils.Constants;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Role;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<CreateUserReq, User> {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User command(CreateUserReq createUserReq) {
        Optional<User> existing = userService.findByMail(createUserReq.getMail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistException(createUserReq.getMail());
        }

        User user = User.fromReq(createUserReq, 200.0);
        String password = passwordEncoder.encode(user.getPassword());
        Optional<Role> optionalRole = roleService.findByRole(Constants.USER_ROLE);
        Role role;
        if (optionalRole.isEmpty()) {
            role = roleService.save(Role.builder().authority(Constants.USER_ROLE).build());

        }else{
            role = optionalRole.get();
        }

        user = user.toBuilder().password(password).roles(Set.of(role)).build();

        return userService.save(user);
    }
}
