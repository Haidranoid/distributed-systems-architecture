package org.dsa.services.authenticationservice.mapper;

import org.dsa.services.authenticationservice.dto.AuthAccountDto;
import org.dsa.services.authenticationservice.dto.AuthResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

  public AuthResponseDto toAuthResponseDto(
      AuthAccountDto accountDto, String accessToken, String refreshToken) {
    return AuthResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .account(accountDto)
        .build();
  }
}
