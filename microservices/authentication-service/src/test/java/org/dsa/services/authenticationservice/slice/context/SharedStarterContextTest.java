package org.dsa.services.authenticationservice.slice.context;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.shared.starter.autoconfig.SecurityAutoConfig;
import org.dsa.shared.starter.autoconfig.SecurityFilterChainAutoConfig;
import org.dsa.shared.starter.autoconfig.SharedStarterAutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

class SharedStarterContextTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(
                  SharedStarterAutoConfig.class,
                  SecurityAutoConfig.class,
                  SecurityFilterChainAutoConfig.class))
          .withInitializer(new ConfigDataApplicationContextInitializer())
          .withSystemProperties("spring.profiles.active=it");

  @Test
  void securityFilterChainIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(SecurityFilterChain.class);
        });
  }

  @Test
  void corsIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(CorsConfigurationSource.class);
        });
  }

  @Test
  void jwtDecoderIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(JwtDecoder.class);
        });
  }
}
