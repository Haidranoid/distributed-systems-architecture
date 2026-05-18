package org.dsa.services.authenticationservice.slice.contexts;

import org.dsa.services.core.servicesstarter.autoconfig.SecurityAutoConfig;
import org.dsa.services.core.servicesstarter.autoconfig.ServicesStarterAutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.assertj.core.api.Assertions.assertThat;

class ServicesStarterContextTest {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            AutoConfigurations.of(
                                    SecurityAutoConfig.class,
                                    ServicesStarterAutoConfig.class
                            )
                    )
                    .withInitializer(
                            new ConfigDataApplicationContextInitializer()
                    )
                    .withSystemProperties(
                            "spring.profiles.active=it"
                    );

    @Test
    void jwtDecoderIsConfigured() {
        contextRunner.run(context -> {
            assertThat(context)
                    .hasSingleBean(JwtDecoder.class);
        });
    }
}

