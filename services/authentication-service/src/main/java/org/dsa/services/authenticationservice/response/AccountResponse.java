package org.dsa.services.authenticationservice.response;

import lombok.Builder;
import org.dsa.core.sharedstarter.constants.Role;

@Builder
public record AccountResponse(
    Long id, String username, String firstName, String lastName, String email, Role role) {}
