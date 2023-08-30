package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity(name = "users")
@Builder
@RequiredArgsConstructor
public class UserModel {
    @Id @GeneratedValue
    Integer id;
    String mail;
    String firstname;
    String lastname;
    Double balance;

    public static UserModel fromDomain(User user){
        return UserModel.builder()
                .id(user.getId())
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .balance(user.getBalance())
                .build();
    }
}
