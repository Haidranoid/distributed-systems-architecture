package org.dsa.core.sharedstarter.autoconfig;

import org.dsa.core.sharedstarter.utils.CurrentSession;
import org.dsa.core.sharedstarter.utils.JwtAuthenticationConverter;
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
