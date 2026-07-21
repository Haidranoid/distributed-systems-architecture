package org.dsa.services.authenticationservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.dsa.services.authenticationservice.constants.FieldType;
import org.jspecify.annotations.NonNull;

@Builder
public record LoginRequest(@NotBlank String username, @NotBlank String password) {
  @Override
  public @NonNull String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("username", username)
        .append("password", FieldType.REDACTED)
        .toString();
  }
}
