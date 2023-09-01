package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserModel {
    @Id @GeneratedValue
    Integer id;
    String mail;
    String firstname;
    String lastname;
    String password;
    Double balance;

    public static UserModel fromDomain(User user){
        return UserModel.builder()
                .id(user.getId())
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .balance(user.getBalance())
                .build();
    }
}
