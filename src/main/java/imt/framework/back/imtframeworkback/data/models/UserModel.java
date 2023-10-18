package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;
    private String mail;
    private String firstname;
    private String lastname;
    private String password;
    private Double balance;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_junction", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleModel> authorities;

    public static UserModel fromDomain(User user) {
        return UserModel.builder()
                .userId(user.getId())
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .balance(user.getBalance())
                .authorities(user.getAuthorities().stream().map(RoleModel::fromDomain).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
