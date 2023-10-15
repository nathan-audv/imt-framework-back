package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.core.utils.Constants;
import imt.framework.back.imtframeworkback.data.models.RoleModel;
import imt.framework.back.imtframeworkback.data.repositories.RoleRepository;
import imt.framework.back.imtframeworkback.data.services.RoleService;
import imt.framework.back.imtframeworkback.domain.models.Role;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    private void setupUserRole() {
        Optional<RoleModel> optionalRole = roleRepository.findByAuthority(Constants.USER_ROLE);
        if (optionalRole.isEmpty()) {
            roleRepository.save(RoleModel.builder().authority(Constants.USER_ROLE).build());
        }
    }
}
