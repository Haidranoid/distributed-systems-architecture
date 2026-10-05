package org.dsa.services.authenticationservice.service;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.properties.JwtProperties;
import org.dsa.shared.core.utils.AlgorithmKeyPair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtSignerService {

  private final JwtProperties jwtProperties;
  private final AlgorithmKeyPair algorithmKeyPair;

  public String generateAccessToken(String subject, Map<String, Object> claims) {
    return buildToken(subject, claims, jwtProperties.accessTokenExpiration());
  }

  public String generateRefreshToken(String subject) {
    return buildToken(subject, Map.of(), jwtProperties.refreshTokenExpiration());
  }

  private String buildToken(String subject, Map<String, Object> claims, long expiration) {
    return Jwts.builder()
        .signWith(algorithmKeyPair.getPrivateKey(), algorithmKeyPair.getSignatureAlgorithm())
        .issuer(jwtProperties.issuer())
        .subject(subject)
        .claims(claims)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .audience()
        .add(jwtProperties.audience())
        .and()
        .compact();
  }
}
