package imt.framework.back.imtframeworkback.data.repositories;

import imt.framework.back.imtframeworkback.data.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByAuthority(String authority);
}
