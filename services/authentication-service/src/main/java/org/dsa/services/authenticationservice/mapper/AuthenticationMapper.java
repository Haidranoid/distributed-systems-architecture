package org.dsa.services.authenticationservice.mapper;

import org.dsa.services.authenticationservice.response.AccountResponse;
import org.dsa.services.authenticationservice.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

  public AuthenticationResponse toAuthResponseDto(
      AccountResponse accountResponse, String accessToken, String refreshToken) {
    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .accountResponse(accountResponse)
        .build();
  }
}
