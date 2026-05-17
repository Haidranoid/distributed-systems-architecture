package org.dsa.services.authenticationservice.common.utils;

import org.dsa.services.core.servicesstarter.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtSignerService {

    private final JwtProperties jwtProperties;
    private final PrivateKey privateKey;
    private final SignatureAlgorithm signatureAlgorithm;

    public String generateAccessToken(String subject, Map<String, Object> claims) {
        return buildToken(subject, claims, jwtProperties.accessTokenExpiration());
    }

    public String generateRefreshToken(String subject) {
        return buildToken(subject, Map.of(), jwtProperties.refreshTokenExpiration());
    }

    private String buildToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .signWith(privateKey, signatureAlgorithm)
                .issuer(jwtProperties.issuer())
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .audience().add(jwtProperties.audience()).and()
                .compact();
    }
}
