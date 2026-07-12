package org.dsa.services.authenticationservice.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
    String accessToken, String refreshToken, AccountResponse accountResponse) {}
