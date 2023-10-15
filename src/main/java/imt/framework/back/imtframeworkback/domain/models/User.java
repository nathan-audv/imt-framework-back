package imt.framework.back.imtframeworkback.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class User implements UserDetails {
    Integer id;
    String mail;
    String firstname;
    String lastname;
    @JsonIgnore
    String password;
    Double balance;
    Set<Role> roles;

    public static User fromReq(CreateUserReq user, Double balance) {
        return User.builder()
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .balance(balance)
                .build();
    }

    public static User fromData(UserModel user) {
        return User.builder()
                .id(user.getUserId())
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .balance(user.getBalance())
                .roles(user.getRoles().stream().map(Role::fromData).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
