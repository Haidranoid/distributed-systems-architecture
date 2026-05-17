package org.dsa.services.authenticationservice.mappers;

import org.dsa.services.authenticationservice.common.dtos.AuthAccountDto;
import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;

public interface AuthenticationMapper {
    AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken);
}
