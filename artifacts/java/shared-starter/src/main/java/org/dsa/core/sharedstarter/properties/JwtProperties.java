package org.dsa.core.sharedstarter.properties;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.security.jwt")
public record JwtProperties(
    String algorithm,
    String privateKey,
    String publicKey,
    Long accessTokenExpiration,
    Long refreshTokenExpiration,
    String issuer,
    List<String> audience) {}
