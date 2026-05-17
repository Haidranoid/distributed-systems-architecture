package org.dsa.services.authenticationservice.common.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthResponseDto(
        String accessToken,
        String refreshToken,
        AuthAccountDto account
) {
    @NonNull
    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "accessToken='" + "[accessToken]" + '\'' +
                ", refreshToken='" + "[refreshToken]" + '\'' +
                ", account=" + account +
                '}';
    }
}