package imt.framework.back.imtframeworkback.domain.usecases.users;

import imt.framework.back.imtframeworkback.core.errors.UserAuthException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticateUserUseCase implements UseCase<AuthUserReq, UserRes> {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserRes command(AuthUserReq request) throws AuthenticationException {
        String mail = request.getMail();
        String password = request.getPassword();
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(mail, password)
            );
        }catch (Exception e){
            throw new UserAuthException(mail);
        }
        String token = tokenService.generateToken(authentication);
        return UserRes.builder()
                .user(userService.findByMail(mail).get())
                .jwt(token)
                .build();
    }
}
