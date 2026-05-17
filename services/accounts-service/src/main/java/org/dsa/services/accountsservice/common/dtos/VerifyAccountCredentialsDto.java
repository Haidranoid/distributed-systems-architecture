package org.dsa.services.accountsservice.common.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record VerifyAccountCredentialsDto(
        String username,
        String password
) {
    @NonNull
    @Override
    public String toString() {
        return "VerifyAccountCredentialsDto{" +
                "username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                '}';
    }
}
