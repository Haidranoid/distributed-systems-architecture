package org.dsa.services.commentsservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("/api/v1").build();
    }
}
