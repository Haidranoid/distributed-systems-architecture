package org.dsa.core.sharedstarter.messaging.events;

import lombok.Builder;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;

@Builder
public record AccountCreatedEvent(Long accountId, String username, String email)
    implements KafkaEvent {}
