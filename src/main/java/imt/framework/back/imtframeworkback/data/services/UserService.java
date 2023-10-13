package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByMail(String mail);

    Optional<User> findById(Integer id);
}
