package org.dsa.shared.starter;

import org.junit.jupiter.api.Test;

class SharedStarterApplicationTests {

  @Test
  void contextLoads() {}

  /*
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
  }*/
}
