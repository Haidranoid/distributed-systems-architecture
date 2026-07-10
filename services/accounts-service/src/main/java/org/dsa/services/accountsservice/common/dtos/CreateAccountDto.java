package org.dsa.services.accountsservice.common.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.dsa.core.sharedstarter.common.constants.Role;
import org.jspecify.annotations.NonNull;

@Builder
public record CreateAccountDto(
        @NotBlank String username,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotNull Role role
) {
    @Override
    public @NonNull String toString() {
        return "CreateAccountDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                ", role=" + role +
                '}';
    }
}
