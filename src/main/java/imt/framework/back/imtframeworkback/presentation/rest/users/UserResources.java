package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.AuthUserReq;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import imt.framework.back.imtframeworkback.domain.results.UserRes;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/users")
public interface UserResources {
    @PostMapping
    void createUser(@RequestBody CreateUserReq createUserReq);

    @PostMapping("/auth")
    UserRes authenticateUser(@RequestBody AuthUserReq authUserReq);

    @PutMapping
    User updateUser(
            @RequestParam Integer userId,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestBody(required = false) String password
    );
}
