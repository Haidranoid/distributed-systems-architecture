package org.dsa.services.authenticationservice.dto;

import lombok.Builder;
import lombok.NonNull;
import org.dsa.core.sharedstarter.common.constants.Role;

@Builder
public record SignupDto(
    String username, String firstName, String lastName, String email, String password, Role role) {
  @NonNull
  @Override
  public String toString() {
    return "SignupDto{"
        + "role="
        + role
        + ", email='"
        + email
        + '\''
        + ", username='"
        + username
        + '\''
        + ", password='"
        + "[password]"
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + '}';
  }
}
