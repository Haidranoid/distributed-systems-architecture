package org.dsa.services.accountsservice.slice.contexts;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.core.sharedstarter.autoconfig.SecurityAutoConfig;
import org.dsa.core.sharedstarter.autoconfig.ServicesStarterAutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.security.oauth2.jwt.JwtDecoder;

class ServicesStarterContextTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(SecurityAutoConfig.class, ServicesStarterAutoConfig.class))
          .withInitializer(new ConfigDataApplicationContextInitializer())
          .withSystemProperties("spring.profiles.active=it");

  @Test
  void jwtDecoderIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(JwtDecoder.class);
        });
  }
}
