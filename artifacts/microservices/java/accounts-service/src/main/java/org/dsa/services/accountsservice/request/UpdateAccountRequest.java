package org.dsa.services.accountsservice.request;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.dsa.core.sharedstarter.constants.Role;
import org.jspecify.annotations.NonNull;

@Builder
public record UpdateAccountRequest(String firstName, String lastName, String email, Role role) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("email", email)
        .add("role", role)
        .toString();
  }
}
