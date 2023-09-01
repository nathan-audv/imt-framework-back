package imt.framework.back.imtframeworkback.core.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
    @Bean
    public PasswordEncoder encoder() {
         return new BCryptPasswordEncoder();
    }
}
