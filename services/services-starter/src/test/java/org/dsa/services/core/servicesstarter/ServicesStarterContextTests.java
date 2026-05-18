package org.dsa.services.core.servicesstarter;

import org.dsa.services.core.servicesstarter.autoconfig.ServicesStarterAutoConfig;
import org.dsa.services.core.servicesstarter.utils.JwtAuthenticationConverter;
import org.dsa.services.core.servicesstarter.utils.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

class ServicesStarterContextTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withUserConfiguration(ServicesStarterAutoConfig.class);

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
