package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.RoleModel;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
@Builder(toBuilder = true)
public class Role implements GrantedAuthority {
    Integer id;
    String authority;

    public static Role fromData(RoleModel roleModel) {
        return Role.builder()
                .id(roleModel.getRoleId())
                .authority(roleModel.getAuthority())
                .build();
    }
}
