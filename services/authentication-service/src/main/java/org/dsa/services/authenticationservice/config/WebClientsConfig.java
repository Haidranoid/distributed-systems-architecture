package org.dsa.services.authenticationservice.config;

import lombok.RequiredArgsConstructor;
import org.dsa.services.authenticationservice.common.properties.Endpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientsConfig {

    private final Endpoints endpoints;

    @Bean
    public WebClient accountsServiceWebClient() {
        return WebClient.builder()
                .baseUrl(endpoints.accountsServiceEndpoint())
                .build();
    }
}
