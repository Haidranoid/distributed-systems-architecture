package org.dsa.services.authenticationservice.utils.webclient;

import lombok.RequiredArgsConstructor;
import org.dsa.services.authenticationservice.response.AccountResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AccountsWebClient {

  private final WebClient accountsServiceWebClient;

  public AccountResponse findByUsername(String username) {
    return accountsServiceWebClient
        .get()
        .uri("/{username}", username)
        .retrieve()
        .bodyToMono(AccountResponse.class)
        .block(); // allows sync mode
  }
}
