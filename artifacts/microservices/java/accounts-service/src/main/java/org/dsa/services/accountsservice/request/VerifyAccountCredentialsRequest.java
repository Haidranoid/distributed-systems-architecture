package org.dsa.services.accountsservice.request;

import com.google.common.base.MoreObjects;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record VerifyAccountCredentialsRequest(
    @NotBlank String username, @NotBlank String password) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("username", username)
        .add("password", "[PROTECTED]")
        .toString();
  }
}
