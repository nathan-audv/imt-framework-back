package imt.framework.back.imtframeworkback.data.repositories;

import imt.framework.back.imtframeworkback.data.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    public Optional<UserModel> findByMail(String mail);
}
