package org.dsa.services.accountsservice.request;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record UpdateAccountPasswordRequest(
    String currentPassword, String newPassword, String confirmationPassword) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("currentPassword", "[PROTECTED]")
        .add("newPassword", "[PROTECTED]")
        .add("confirmationPassword", "[PROTECTED]")
        .toString();
  }
}
