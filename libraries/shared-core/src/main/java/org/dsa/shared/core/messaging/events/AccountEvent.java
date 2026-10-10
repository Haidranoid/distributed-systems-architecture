package org.dsa.shared.core.messaging.events;

import org.dsa.shared.core.messaging.contracts.KafkaEvent;

public interface AccountEvent extends KafkaEvent {
  Long accountId();
}
