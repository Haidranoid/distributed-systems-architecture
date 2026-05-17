package org.dsa.services.authenticationservice.controllers;

import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;
import org.dsa.services.authenticationservice.common.dtos.LoginDto;
import org.dsa.services.authenticationservice.common.dtos.SignupDto;
import org.dsa.services.authenticationservice.services.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "AuthenticationController")
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponseDto login(@RequestBody LoginDto loginDto) {
        log.info("login request body: {}", loginDto);

        var loginResponse = authenticationService.login(loginDto);

        log.info("login request response: {}", loginResponse);
        return loginResponse;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto signup(@RequestBody SignupDto signupDto) {
        log.info("signup request body: {}", signupDto);

        var signupResponse = authenticationService.signup(signupDto);

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
