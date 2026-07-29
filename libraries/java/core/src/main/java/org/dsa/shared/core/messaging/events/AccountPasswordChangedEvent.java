package org.dsa.shared.core.messaging.events;

import lombok.Builder;
import org.dsa.shared.core.messaging.contracts.KafkaEvent;

@Builder
public record AccountPasswordChangedEvent(Long accountId, String username, String email)
    implements KafkaEvent {}
