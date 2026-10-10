package org.dsa.shared.core.utils;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Getter;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Getter
public class JsonWebTokenDecoder {
  private final NimbusJwtDecoder nimbusDecoder;

  public JsonWebTokenDecoder(String issuer, PublicKey publicKey) {
    this.nimbusDecoder = this.generateNimbusJwtDecoder(issuer, publicKey);
  }

  private NimbusJwtDecoder generateNimbusJwtDecoder(String issuer, PublicKey publicKey) {
    NimbusJwtDecoder nimbusDecoder =
        NimbusJwtDecoder.withPublicKey((RSAPublicKey) publicKey).build();
    OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);

    nimbusDecoder.setJwtValidator(validator);

    return nimbusDecoder;
  }
}
