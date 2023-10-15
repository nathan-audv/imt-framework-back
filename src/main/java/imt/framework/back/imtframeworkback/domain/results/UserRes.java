package imt.framework.back.imtframeworkback.domain.results;

import imt.framework.back.imtframeworkback.domain.models.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRes {
    User user;
    String jwt;
}
