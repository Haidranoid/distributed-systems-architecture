package org.dsa.services.accountsservice.common.dtos;

import lombok.Builder;
import org.dsa.core.sharedstarter.common.constants.Role;

@Builder
public record AccountDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
