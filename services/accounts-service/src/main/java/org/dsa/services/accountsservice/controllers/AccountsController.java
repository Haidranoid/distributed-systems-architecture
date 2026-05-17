package org.dsa.services.accountsservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountPasswordDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountDto;
import org.dsa.services.accountsservice.services.impl.AccountsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "AccountsController")
@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsServiceImpl accountsService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @SecurityRequirement(name = "bearerAuth")
    public AccountDto getMe() {
        log.info("getMe request started");

        var currentSession = accountsService.me();

        log.info("getMe request response: {}", currentSession);
        return currentSession;
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        log.info("getAllAccounts request started");

        var accountList = accountsService.findAll();

        log.info("getAllAccounts request response: [{}]", "...");
        return accountList;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get an account by id")
    public AccountDto getAccountById(@PathVariable Long userId) {
        log.info("getAccountById request: {}", userId);

        var account = accountsService.findById(userId);

        log.info("getAccountById response: {}", account);
        return account;
    }

    @PatchMapping("/{userId}")
    public AccountDto updateAccount(
            @PathVariable Long userId,
            @RequestBody UpdateAccountDto updateAccountDto
    ) {
        log.info("updateAccount request body: {}", updateAccountDto);

        var accountUpdated = accountsService.update(userId, updateAccountDto);

        log.info("updateAccount request response: {}", accountUpdated);
        return accountUpdated;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long userId) {
        log.info("deleteAccount request: {}", userId);

        accountsService.delete(userId);

        log.info("deleteAccount request response: {}", userId);
    }

    @PatchMapping("/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody UpdateAccountPasswordDto updateAccountPasswordDto) {
        log.info("changePassword request: {}", updateAccountPasswordDto);

        //accountsService.changePassword(changePasswordAccountDto);

        log.info("changePassword response: {}", "success");
    }
}
