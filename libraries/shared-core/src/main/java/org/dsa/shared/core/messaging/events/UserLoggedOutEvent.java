package org.dsa.shared.core.messaging.events;

import lombok.Builder;
import org.dsa.shared.core.messaging.contracts.AuthEvent;

@Builder
public record UserLoggedOutEvent(Long accountId, String username, String email)
    implements AuthEvent {

  @Override
  public String getEventKey() {
    return accountId.toString();
  }
}
