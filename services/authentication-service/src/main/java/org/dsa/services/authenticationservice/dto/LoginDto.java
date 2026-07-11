package org.dsa.services.authenticationservice.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record LoginDto(String username, String password) {
  @NonNull
  @Override
  public String toString() {
    return "LoginDto{"
        + "username='"
        + username
        + '\''
        + ", password='"
        + "[password]"
        + '\''
        + '}';
  }
}
