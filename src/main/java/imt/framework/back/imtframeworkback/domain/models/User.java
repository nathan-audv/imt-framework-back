package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.domain.requests.UserReq;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
    Integer id;
    String mail;
    String firstname;
    String lastname;
    Double balance;

    public static User fromReq(UserReq user){
        return User.builder()
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
