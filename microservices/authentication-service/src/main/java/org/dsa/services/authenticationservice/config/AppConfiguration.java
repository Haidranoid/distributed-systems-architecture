package org.dsa.services.authenticationservice.config;

import org.dsa.services.authenticationservice.properties.Endpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient accountsServiceWebClient(Endpoints endpoints) {
        return WebClient.builder().baseUrl(endpoints.accountsServiceEndpoint()).build();
    }
}
