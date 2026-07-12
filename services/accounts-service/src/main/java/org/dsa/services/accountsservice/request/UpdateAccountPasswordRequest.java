package org.dsa.services.accountsservice.request;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpdateAccountPasswordRequest(
    String currentPassword, String newPassword, String confirmationPassword) {
  @NonNull
  @Override
  public String toString() {
    return "UpdateAccountPasswordDto{}";
  }
}
