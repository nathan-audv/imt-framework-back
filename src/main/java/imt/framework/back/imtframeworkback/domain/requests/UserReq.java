package imt.framework.back.imtframeworkback.domain.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserReq {
    String mail;
    String firstname;
    String lastname;
    String password;
}
