package org.dsa.services.authenticationservice.response;

import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.dsa.core.sharedstarter.constants.Role;
import org.jspecify.annotations.NonNull;

@Builder
public record AccountResponse(
    Long id, String username, String firstName, String lastName, String email, Role role) {
  @Override
  public @NonNull String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("username", username)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("email", email)
        .append("role", role)
        .toString();
  }
}
