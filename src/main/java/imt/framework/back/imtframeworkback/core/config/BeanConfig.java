package imt.framework.back.imtframeworkback.core.config;

import imt.framework.back.imtframeworkback.core.utils.RsaKeyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(RsaKeyProperties.class)
@Configuration
public class BeanConfig {
}
