package org.dsa.services.authenticationservice.common.dtos;

import lombok.NonNull;
import org.dsa.services.core.servicesstarter.constants.Role;
import lombok.Builder;

@Builder
public record SignupDto(
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
        return "SignupDto{" +
                "role=" + role +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
