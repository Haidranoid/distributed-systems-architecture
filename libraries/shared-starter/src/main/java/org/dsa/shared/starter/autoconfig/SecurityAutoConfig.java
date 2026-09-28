package org.dsa.shared.starter.autoconfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.dsa.shared.starter.properties.JwtProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class SecurityAutoConfig {

  private final JwtProperties jwtProperties;

  @Bean
  public SignatureAlgorithm signatureAlgorithm() {
    return (SignatureAlgorithm) Jwts.SIG.get().get(jwtProperties.algorithm());
  }

  @Bean
  @ConditionalOnProperty(name = "application.security.jwt.private-key")
  public PrivateKey privateKey(SignatureAlgorithm signatureAlgorithm) throws Exception {
    byte[] decoded = decode(jwtProperties.privateKey());

    return keyFactory(signatureAlgorithm).generatePrivate(new PKCS8EncodedKeySpec(decoded));
  }

  @Bean
  @ConditionalOnProperty(name = "application.security.jwt.public-key")
  public PublicKey publicKey(SignatureAlgorithm signatureAlgorithm) throws Exception {
    byte[] decoded = decode(jwtProperties.publicKey());

    return keyFactory(signatureAlgorithm).generatePublic(new X509EncodedKeySpec(decoded));
  }

  private KeyFactory keyFactory(SignatureAlgorithm algorithm) throws Exception {
    String jcaName = algorithm.getId(); // RS256, ES256, etc.
    String keyType = jcaName.startsWith("ES") ? "EC" : "RSA";

    return KeyFactory.getInstance(keyType);
  }

  // PEM/Base64
  private byte[] decode(String key) {
    return Base64.getDecoder()
        .decode(
            key.replaceAll("-----BEGIN(.*?)-----", "")
                .replaceAll("-----END(.*?)-----", "")
                .replaceAll("\\s", ""));
  }
}
