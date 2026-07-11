package org.dsa.services.accountsservice.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpdateAccountPasswordDto(
    String currentPassword, String newPassword, String confirmationPassword) {
  @NonNull
  @Override
  public String toString() {
    return "UpdateAccountPasswordDto{}";
  }
}
