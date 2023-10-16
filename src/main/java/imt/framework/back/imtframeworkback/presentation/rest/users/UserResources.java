package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/users")
public interface UserResources {
    @PostMapping
    void createUser(@RequestBody CreateUserReq createUserReq);

    @PostMapping("/auth")
    UserRes authenticateUser(@RequestBody AuthUserReq authUserReq);
}
