package org.dsa.shared.core.messaging.events;

import lombok.Builder;

@Builder
public record AccountUpdatedEvent(Long accountId, String username, String email)
    implements AccountEvent {}
