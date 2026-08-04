package org.dsa.shared.starter;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.shared.starter.autoconfig.SharedStarterAutoConfig;
import org.dsa.shared.core.utils.CurrentSession;
import org.dsa.shared.core.utils.JwtAuthenticationConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.client.RestTemplate;

class SharedStarterApplicationTests {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner().withUserConfiguration(SharedStarterAutoConfig.class);

  @Test
  void jwtAuthenticationConverterIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(JwtAuthenticationConverter.class);
        });
  }

  @Test
  void sessionServiceIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(CurrentSession.class);
        });
  }

  @Test
  void restTemplateIsConfigured() {
    contextRunner.run(
        context -> {
          assertThat(context).hasSingleBean(RestTemplate.class);
        });
  }
}
