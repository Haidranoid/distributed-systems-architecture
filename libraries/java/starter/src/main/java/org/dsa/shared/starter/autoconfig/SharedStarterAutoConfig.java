package org.dsa.shared.starter.autoconfig;

import org.dsa.shared.starter.utils.CurrentSession;
import org.dsa.shared.starter.utils.JwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
public class SharedStarterAutoConfig {

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    return new JwtAuthenticationConverter();
  }

  @Bean
  public CurrentSession currentSession() {
    return new CurrentSession();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
