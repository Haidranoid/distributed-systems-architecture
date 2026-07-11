package org.dsa.services.authenticationservice.dto;

import lombok.Builder;
import org.dsa.core.sharedstarter.common.constants.Role;

@Builder
public record AuthAccountDto(
    Long id, String username, String firstName, String lastName, String email, Role role) {}
