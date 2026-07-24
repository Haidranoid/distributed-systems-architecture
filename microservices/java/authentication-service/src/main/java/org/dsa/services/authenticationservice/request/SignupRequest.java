package org.dsa.services.authenticationservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.dsa.core.sharedstarter.constants.Role;
import org.dsa.services.authenticationservice.constants.FieldType;
import org.jspecify.annotations.NonNull;

@Builder
public record SignupRequest(
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotNull Role role) {
  @Override
  public @NonNull String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("username", username)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("email", email)
        .append("password", FieldType.REDACTED)
        .append("role", role)
        .toString();
  }
}
