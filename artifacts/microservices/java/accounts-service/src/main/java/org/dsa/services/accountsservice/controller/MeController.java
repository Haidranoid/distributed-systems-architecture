package org.dsa.services.accountsservice.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.request.UpdateAccountPasswordRequest;
import org.dsa.services.accountsservice.response.AccountResponse;
import org.dsa.services.accountsservice.service.MeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "MeController")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/accounts/me")
@RestController
@RequiredArgsConstructor
public class MeController {

  private final MeService meService;

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public AccountResponse getMe() {
    log.info("getMe request started");

    var currentSession = meService.me();

    log.info("getMe request response: {}", currentSession);
    return currentSession;
  }

  @PatchMapping("/change-password")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void meChangePassword(
      @RequestBody UpdateAccountPasswordRequest updateAccountPasswordRequest) {
    log.info("changePassword request: {}", updateAccountPasswordRequest);

    // accountService.changePassword(changePasswordAccountDto);

    log.info("changePassword response: {}", "success");
  }
}
