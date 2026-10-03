package org.dsa.shared.core.messaging.events;

import lombok.Builder;
import org.dsa.shared.core.messaging.contracts.AccountEvent;

@Builder
public record AccountUpdatedEvent(Long accountId, String username, String email)
    implements AccountEvent {

  @Override
  public String getEventKey() {
    return accountId.toString();
  }
}
