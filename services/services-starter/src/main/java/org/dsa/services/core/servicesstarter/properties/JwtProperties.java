package org.dsa.services.core.servicesstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.security.jwt")
public record JwtProperties(
        String algorithm,
        String privateKey,
        String publicKey,
        Long accessTokenExpiration,
        Long refreshTokenExpiration,
        String issuer,
        List<String> audience
) {
}
