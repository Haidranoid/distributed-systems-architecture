package org.dsa.services.authenticationservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.authenticationservice.request.LoginRequest;
import org.dsa.services.authenticationservice.request.SignupRequest;
import org.dsa.services.authenticationservice.response.AuthenticationResponse;
import org.dsa.services.authenticationservice.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "AuthenticationController")
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
    log.info("login request body: {}", loginRequest);

    var loginResponse = authenticationService.login(loginRequest);

    log.info("login request response: {}", loginResponse);
    return loginResponse;
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
    log.info("signup request body: {}", signupRequest);

    var signupResponse = authenticationService.signup(signupRequest);

    log.info("signup request response: {}", signupResponse);
    return signupResponse;
  }

  /*
  @PostMapping("/refresh-token")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @SecurityRequirement(name = "bearerAuth")
  public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
      authenticationService.refreshToken(request, response);
  }
  */
}
