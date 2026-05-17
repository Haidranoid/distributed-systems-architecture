package org.dsa.services.accountsservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.VerifyAccountCredentialsDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.services.impl.AccountsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "InternalAccountController", description = "Internal endpoints. Do NOT call directly from client applications.")
@RequestMapping("/api/v1/internal/accounts")
@RestController
@RequiredArgsConstructor
public class InternalAccountsController {

    private final AccountsServiceImpl accountsServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates an account")
    public AccountDto createAccount(@RequestBody CreateAccountDto createAccountDto) {
        log.info("createAccount request body: {}", createAccountDto);

        var account = accountsServiceImpl.create(createAccountDto);

        log.info("createAccount request response: {}", account);
        return account;
    }

    @PostMapping("/authenticate-login")
    @Operation(summary = "Authenticate if the user exists, if so, return it.")
    public AccountDto verifyAccountCredentials(
            @RequestBody VerifyAccountCredentialsDto verifyAccountCredentialsDto
    ) {
        log.info("verifyAccountCredentials request body: {}", verifyAccountCredentialsDto);

        var account = accountsServiceImpl.verifyCredentials(verifyAccountCredentialsDto);

        log.info("verifyAccountCredentials request response: {}", account);
        return account;
    }
}
