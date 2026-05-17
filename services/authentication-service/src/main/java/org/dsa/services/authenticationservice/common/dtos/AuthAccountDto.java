package org.dsa.services.authenticationservice.common.dtos;

import org.dsa.services.core.servicesstarter.constants.Role;
import lombok.Builder;

@Builder
public record AuthAccountDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}