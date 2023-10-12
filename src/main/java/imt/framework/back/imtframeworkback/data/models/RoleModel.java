package imt.framework.back.imtframeworkback.data.models;

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
    Integer id;
    String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
