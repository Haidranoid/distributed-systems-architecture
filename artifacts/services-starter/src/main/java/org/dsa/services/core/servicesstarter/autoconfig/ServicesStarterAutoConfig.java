package org.dsa.services.core.servicesstarter.autoconfig;

import org.dsa.services.core.servicesstarter.utils.JwtAuthenticationConverter;
import org.dsa.services.core.servicesstarter.utils.SessionService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
public class ServicesStarterAutoConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public SessionService sessionUtils() {
        return new SessionService();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
