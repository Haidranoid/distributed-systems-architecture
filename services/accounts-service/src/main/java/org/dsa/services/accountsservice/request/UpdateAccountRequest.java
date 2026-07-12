package org.dsa.services.accountsservice.request;

import lombok.Builder;
import org.dsa.core.sharedstarter.constants.Role;

@Builder
public record UpdateAccountRequest(String firstName, String lastName, String email, Role role) {}
