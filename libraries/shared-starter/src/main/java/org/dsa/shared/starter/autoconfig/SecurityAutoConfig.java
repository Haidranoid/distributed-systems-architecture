package org.dsa.shared.starter.autoconfig;

import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.properties.JwtProperties;
import org.dsa.shared.core.utils.AlgorithmKeyPair;
import org.dsa.shared.core.utils.CurrentSession;
import org.dsa.shared.core.utils.JsonWebTokenDecoder;
import org.dsa.shared.core.utils.JwtAuthenticationConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class SecurityAutoConfig {

  private final JwtProperties jwtProperties;

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    return new JwtAuthenticationConverter();
  }

  @Bean
  public AlgorithmKeyPair algorithmKeyPair() throws Exception {
    return new AlgorithmKeyPair(
        jwtProperties.algorithm(), jwtProperties.publicKey(), jwtProperties.privateKey());
  }

  @Bean
  public JsonWebTokenDecoder jsonWebTokenDecoder(AlgorithmKeyPair algorithmKeyPair) {
    return new JsonWebTokenDecoder(jwtProperties.issuer(), algorithmKeyPair.getPublicKey());
  }

  @Bean
  public CurrentSession currentSession() {
    return new CurrentSession();
  }
}
