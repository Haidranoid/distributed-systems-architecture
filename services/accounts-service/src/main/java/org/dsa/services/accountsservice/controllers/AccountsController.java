package org.dsa.services.accountsservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountPasswordDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountDto;
import org.dsa.services.accountsservice.services.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "AccountsController")
@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountServiceImpl accountService;

    @GetMapping
    @Operation(summary = "Get all accounts")
    public List<AccountDto> getAllAccounts() {
        log.info("getAllAccounts request started");

        var accountList = accountService.findAll();

        log.info("getAllAccounts request response: [{}]", "...");
        return accountList;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get an account by id")
    public AccountDto getAccountById(@PathVariable Long userId) {
        log.info("getAccountById request: {}", userId);

        var account = accountService.findById(userId);

        log.info("getAccountById response: {}", account);
        return account;
    }

    @PatchMapping("/{userId}")
    @Operation(summary = "Update an account by id")
    public AccountDto updateAccount(
            @PathVariable Long userId,
            @RequestBody UpdateAccountDto updateAccountDto
    ) {
        log.info("updateAccount request body: {}", updateAccountDto);

        var accountUpdated = accountService.update(userId, updateAccountDto);

        log.info("updateAccount request response: {}", accountUpdated);
        return accountUpdated;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an account by id")
    public void deleteAccount(@PathVariable Long userId) {
        log.info("deleteAccount request: {}", userId);

        accountService.delete(userId);

        log.info("deleteAccount request response: {}", userId);
    }

    @PatchMapping("/{userId}/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Change password of an account by id")
    public void changePassword(
            @PathVariable Long userId,
            @RequestBody UpdateAccountPasswordDto updateAccountPasswordDto
    ) {
        log.info("changePassword request: {}", updateAccountPasswordDto);

        //accountService.changePassword(changePasswordAccountDto);

        log.info("changePassword response: {}", "success");
    }
}
