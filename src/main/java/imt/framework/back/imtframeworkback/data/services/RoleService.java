package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRole(String role);

    Role save(Role role);
}
