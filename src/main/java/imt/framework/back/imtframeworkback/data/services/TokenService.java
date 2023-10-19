package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.User;
import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);

    User getMail();
}
