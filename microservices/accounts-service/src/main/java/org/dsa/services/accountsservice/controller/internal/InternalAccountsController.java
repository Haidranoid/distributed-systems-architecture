package org.dsa.services.accountsservice.controller.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.request.CreateAccountRequest;
import org.dsa.services.accountsservice.request.VerifyAccountCredentialsRequest;
import org.dsa.services.accountsservice.response.AccountResponse;
import org.dsa.services.accountsservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
    name = "InternalAccountController",
    description = "Internal endpoints. Do NOT call directly from client applications.")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/internal/accounts")
@RestController
@RequiredArgsConstructor
public class InternalAccountsController {

  private final AccountService accountService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Creates an account")
  public AccountResponse createAccount(
      @Valid @RequestBody CreateAccountRequest createAccountRequest) {
    log.info("createAccount request body: {}", createAccountRequest);

    var account = accountService.create(createAccountRequest);

    log.info("createAccount request response: {}", account);
    return account;
  }

  @PostMapping("/verify-credentials")
  @Operation(summary = "Verify if the accounts exists, if so, return it.")
  public AccountResponse verifyAccountCredentials(
      @Valid @RequestBody VerifyAccountCredentialsRequest verifyAccountCredentialsRequest) {
    log.info("verifyAccountCredentials request body: {}", verifyAccountCredentialsRequest);

    var account = accountService.verifyCredentials(verifyAccountCredentialsRequest);

    log.info("verifyAccountCredentials request response: {}", account);
    return account;
  }
}
