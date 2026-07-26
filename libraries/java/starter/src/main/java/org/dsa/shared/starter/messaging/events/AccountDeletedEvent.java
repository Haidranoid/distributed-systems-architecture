package org.dsa.shared.starter.messaging.events;

import lombok.Builder;
import org.dsa.shared.starter.messaging.contracts.KafkaEvent;

@Builder
public record AccountDeletedEvent(Long accountId, String username, String email)
    implements KafkaEvent {}
