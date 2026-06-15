package org.dsa.services.authenticationservice.messaging.events;

import lombok.Builder;

@Builder
public record AccountCreatedEvent(
        Long accountId,
        String email
) {
}
