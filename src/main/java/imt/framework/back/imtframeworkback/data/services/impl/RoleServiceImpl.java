package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.data.models.RoleModel;
import imt.framework.back.imtframeworkback.data.repositories.RoleRepository;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.domain.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRole(String role) {
        return roleRepository.findByAuthority(role).map(Role::fromData);
    }

    @Override
    public Role save(Role role) {
        return Role.fromData(roleRepository.save(RoleModel.fromDomain(role)));
    }
}
