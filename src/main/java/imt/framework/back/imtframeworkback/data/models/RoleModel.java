package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    Integer roleId;
    String authority;

    public static RoleModel fromDomain(Role role){
        return RoleModel.builder()
                .roleId(role.getId())
                .authority(role.getAuthority())
                .build();
    }
}
