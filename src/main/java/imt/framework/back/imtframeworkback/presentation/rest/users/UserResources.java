package imt.framework.back.imtframeworkback.presentation.rest.users;

import imt.framework.back.imtframeworkback.domain.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/users")
public interface UserResources {
    @PostMapping
    User createUser(
            @RequestParam String mail,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String password
    );

    @GetMapping
    User authenticateUser(
            @RequestParam String mail,
            @RequestParam String password
    );
}
