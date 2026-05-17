package org.dsa.services.authenticationservice.common.webclients;

import lombok.RequiredArgsConstructor;
import org.dsa.services.authenticationservice.common.dtos.AuthAccountDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AccountsWebClient {

    private final WebClient accountsServiceWebClient;

    public AuthAccountDto findByUsername(String username) {
        return accountsServiceWebClient
                .get()
                .uri("/{username}", username)
                .retrieve()
                .bodyToMono(AuthAccountDto.class)
                .block(); // allows sync mode
    }
}
