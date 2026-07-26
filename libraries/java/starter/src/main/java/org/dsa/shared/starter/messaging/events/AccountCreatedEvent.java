package org.dsa.shared.starter.messaging.events;

import lombok.Builder;
import org.dsa.shared.starter.messaging.contracts.KafkaEvent;

@Builder
public record AccountCreatedEvent(Long accountId, String username, String email)
    implements KafkaEvent {}
