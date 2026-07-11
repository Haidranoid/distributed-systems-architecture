package org.dsa.core.sharedstarter;

import org.dsa.core.sharedstarter.autoconfig.SharedStarterAutoConfig;
import org.dsa.core.sharedstarter.utils.JwtAuthenticationConverter;
import org.dsa.core.sharedstarter.utils.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

class SharedStarterApplicationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withUserConfiguration(SharedStarterAutoConfig.class);

    @Test
    void jwtAuthenticationConverterIsConfigured() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(JwtAuthenticationConverter.class);
        });
    }

    @Test
    void sessionServiceIsConfigured() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(SessionService.class);
        });
    }

    @Test
    void restTemplateIsConfigured() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(RestTemplate.class);
        });
    }
}
