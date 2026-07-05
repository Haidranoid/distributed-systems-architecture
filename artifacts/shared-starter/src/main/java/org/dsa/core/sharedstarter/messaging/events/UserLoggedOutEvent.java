package org.dsa.core.sharedstarter.messaging.events;

import lombok.Builder;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;

@Builder
public record UserLoggedOutEvent(
        Long accountId,
        String username,
        String email
) implements KafkaEvent {
}
