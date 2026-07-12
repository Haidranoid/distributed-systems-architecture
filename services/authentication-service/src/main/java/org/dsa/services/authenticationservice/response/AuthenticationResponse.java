package org.dsa.services.authenticationservice.response;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record AuthenticationResponse(
    String accessToken, String refreshToken, AccountResponse accountResponse) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("accessToken", "[PROTECTED]")
        .add("refreshToken", "[PROTECTED]")
        .add("accountResponse", accountResponse)
        .toString();
  }
}
