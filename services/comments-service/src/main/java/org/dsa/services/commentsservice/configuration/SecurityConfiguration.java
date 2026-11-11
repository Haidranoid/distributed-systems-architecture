package org.dsa.services.commentsservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private static final String[] SWAGGER_LIST_URL = {
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**",
      "/swagger-ui.html"
  };

  private static final String[] WHITE_LIST_URL = {
      "/actuator/**", "/api/v1/**",
  };

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .authorizeExchange(
            exchange ->
                exchange
                    .pathMatchers(SWAGGER_LIST_URL)
                    .permitAll()
                    .pathMatchers(WHITE_LIST_URL)
                    .permitAll()
                    .anyExchange()
                    // .authenticated() - TODO
                    .permitAll()
        )
        .build();
  }
}
