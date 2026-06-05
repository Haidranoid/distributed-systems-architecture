package org.dsa.services.accountsservice.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountPasswordDto;
import org.dsa.services.accountsservice.services.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "MeController")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/accounts/me")
@RestController
@RequiredArgsConstructor
public class MeController {

    private final AccountServiceImpl accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDto getMe() {
        log.info("getMe request started");

        var currentSession = accountService.me();

        log.info("getMe request response: {}", currentSession);
        return currentSession;
    }

    @PatchMapping("/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void meChangePassword(@RequestBody UpdateAccountPasswordDto updateAccountPasswordDto) {
        log.info("changePassword request: {}", updateAccountPasswordDto);

        //accountService.changePassword(changePasswordAccountDto);

        log.info("changePassword response: {}", "success");
    }
}
