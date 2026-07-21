package org.dsa.services.accountsservice.request;

import com.google.common.base.MoreObjects;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.dsa.core.sharedstarter.constants.Role;
import org.jspecify.annotations.NonNull;

@Builder
public record CreateAccountRequest(
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotNull Role role) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("username", username)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("email", email)
        .add("password", "[PROTECTED]")
        .add("role", role)
        .toString();
  }
}
