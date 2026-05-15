package org.dsa.services.core.servicesstarter.autoconfig;

import lombok.RequiredArgsConstructor;
import org.dsa.services.core.servicesstarter.properties.JwtProperties;
import org.dsa.services.core.servicesstarter.security.JwtAuthenticationConverter;
import org.dsa.services.core.servicesstarter.utils.SessionUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@AutoConfiguration
@RequiredArgsConstructor
public class SecurityAutoConfig {

    private final JwtProperties jwtProperties;

    @Bean
    public JwtDecoder jwtDecoder(@Nullable PublicKey publicKey) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey((RSAPublicKey) publicKey).build();
        OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(jwtProperties.issuer());

        decoder.setJwtValidator(validator);

        return decoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public SessionUtils sessionUtils() {
        return new SessionUtils();
    }
}
