package org.dsa.services.authenticationservice.response;

import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.dsa.services.authenticationservice.constants.FieldType;
import org.jspecify.annotations.NonNull;

@Builder
public record AuthenticationResponse(
    String accessToken, String refreshToken, AccountResponse accountResponse) {
  @Override
  public @NonNull String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("accessToken", FieldType.REDACTED)
        .append("refreshToken", FieldType.REDACTED)
        .append("accountResponse", accountResponse)
        .toString();
  }
}
