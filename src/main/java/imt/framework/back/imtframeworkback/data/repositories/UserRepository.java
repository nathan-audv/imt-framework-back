package imt.framework.back.imtframeworkback.data.repositories;

import imt.framework.back.imtframeworkback.data.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
