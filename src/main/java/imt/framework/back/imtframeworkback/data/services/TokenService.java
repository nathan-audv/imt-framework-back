package imt.framework.back.imtframeworkback.data.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public interface TokenService {
    String generateToken(Authentication authentication);

    static boolean isUserValid(int userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (long) ((Jwt) authentication.getPrincipal()).getClaims().get("userId") == userId;
        }
        return false;
    }
}
