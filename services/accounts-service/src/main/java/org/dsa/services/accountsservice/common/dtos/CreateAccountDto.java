package org.dsa.services.accountsservice.common.dtos;

import lombok.Builder;
import lombok.NonNull;
import org.dsa.services.core.servicesstarter.constants.Role;

@Builder
public record CreateAccountDto(
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        Role role
) {
    @NonNull
    @Override
    public String toString() {
        return "CreateAccountDto{" +
                "role=" + role +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
