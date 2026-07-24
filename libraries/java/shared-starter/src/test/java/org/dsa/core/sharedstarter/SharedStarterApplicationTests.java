package org.dsa.core.sharedstarter;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.core.sharedstarter.autoconfig.SharedStarterAutoConfig;
import org.dsa.core.sharedstarter.utils.CurrentSession;
import org.dsa.core.sharedstarter.utils.JwtAuthenticationConverter;
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
