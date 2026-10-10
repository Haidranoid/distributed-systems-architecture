package org.dsa.services.accountsservice.dtos.request;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record DeleteAccountRequest(Long id) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).toString();
  }
}
