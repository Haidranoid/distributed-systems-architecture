package org.dsa.shared.core.messaging.events;

import lombok.Builder;

@Builder
public record UserLoggedOutEvent(Long accountId, String username, String email)
    implements AuthEvent {}
