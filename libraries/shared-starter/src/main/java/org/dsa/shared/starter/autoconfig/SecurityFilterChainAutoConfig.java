package org.dsa.shared.starter.autoconfig;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.properties.JwtProperties;
import org.dsa.shared.core.utils.JwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@AutoConfiguration(after = SecurityAutoConfig.class)
@EnableConfigurationProperties(JwtProperties.class)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainAutoConfig {

  private final JwtProperties jwtProperties;
  private final PublicKey publicKey;

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
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    http.csrf(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            req ->
                req.requestMatchers(SWAGGER_LIST_URL)
                    .permitAll()
                    .requestMatchers(WHITE_LIST_URL)
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(
                    jwt ->
                        jwt.decoder(jwtDecoder())
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())));
    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    return new JwtAuthenticationConverter();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey((RSAPublicKey) publicKey).build();
    OAuth2TokenValidator<Jwt> validator =
        JwtValidators.createDefaultWithIssuer(jwtProperties.issuer());

    decoder.setJwtValidator(validator);

    return decoder;
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("OPTIONS");
    configuration.addAllowedMethod("GET");
    configuration.addAllowedMethod("PUT");
    configuration.addAllowedMethod("POST");
    configuration.addAllowedMethod("PATCH");
    configuration.addAllowedMethod("DELETE");
    configuration.addAllowedHeader("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
