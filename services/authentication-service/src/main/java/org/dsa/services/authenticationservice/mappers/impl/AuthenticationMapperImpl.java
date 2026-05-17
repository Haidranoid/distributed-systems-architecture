package org.dsa.services.authenticationservice.mappers.impl;

import org.dsa.services.authenticationservice.common.dtos.AuthAccountDto;
import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;
import org.dsa.services.authenticationservice.mappers.AuthenticationMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapperImpl implements AuthenticationMapper {

    @Override
    public AuthResponseDto toAuthResponseDto(AuthAccountDto accountDto, String accessToken, String refreshToken) {
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(accountDto)
                .build();
    }
}