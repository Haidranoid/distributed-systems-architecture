package org.dsa.services.commentsservice.utils.webclient;

import lombok.RequiredArgsConstructor;
import org.dsa.services.commentsservice.dto.response.AccountResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CommentsWebClient {

    private static final String ACCOUNTS_URL_API = "/accounts";

    private final WebClient webClient;

    public AccountResponse findByUsername(String username) {
        return webClient
                .get()
                .uri(ACCOUNTS_URL_API + "/{username}", username)
                .retrieve()
                .bodyToMono(AccountResponse.class)
                .block(); // allows sync mode
    }
}
