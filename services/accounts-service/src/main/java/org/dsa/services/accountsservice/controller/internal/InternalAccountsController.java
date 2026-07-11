package org.dsa.services.accountsservice.controller.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.dto.CreateAccountDto;
import org.dsa.services.accountsservice.dto.VerifyAccountCredentialsDto;
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
  public AccountDto createAccount(@Valid @RequestBody CreateAccountDto createAccountDto) {
    log.info("createAccount request body: {}", createAccountDto);

    var account = accountService.create(createAccountDto);

    log.info("createAccount request response: {}", account);
    return account;
  }

  @PostMapping("/verify-credentials")
  @Operation(summary = "Verify if the accounts exists, if so, return it.")
  public AccountDto verifyAccountCredentials(
      @Valid @RequestBody VerifyAccountCredentialsDto verifyAccountCredentialsDto) {
    log.info("verifyAccountCredentials request body: {}", verifyAccountCredentialsDto);

    var account = accountService.verifyCredentials(verifyAccountCredentialsDto);

    log.info("verifyAccountCredentials request response: {}", account);
    return account;
  }
}
