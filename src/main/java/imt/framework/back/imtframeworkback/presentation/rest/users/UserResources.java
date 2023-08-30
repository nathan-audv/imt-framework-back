package imt.framework.back.imtframeworkback.presentation.rest.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/users")
public interface UserResources {
    @PostMapping
    void createUser(
            @RequestParam String mail,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String password
    );
}
