package org.dsa.services.accountsservice.common.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record VerifyAccountCredentialsDto(
        @NotBlank String username,
        @NotBlank String password
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
