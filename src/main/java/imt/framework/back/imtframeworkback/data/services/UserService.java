package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.User;

import java.util.Optional;

public interface UserService {
    public void save(User user);
    public Optional<User> findByMail(String mail);
}
