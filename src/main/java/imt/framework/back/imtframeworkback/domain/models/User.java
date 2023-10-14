package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.domain.requests.CreateUserReq;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
    Integer id;
    String mail;
    String firstname;
    String lastname;
    String password;
    Double balance;

    public static User fromReq(CreateUserReq user) {
        return User.builder()
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
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
                .build();
    }
}
