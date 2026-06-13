package org.dsa.services.authenticationservice.messaging.events;

import java.util.UUID;

public record AccountCreatedEvent(
        UUID accountId,
        String email
) {
}
