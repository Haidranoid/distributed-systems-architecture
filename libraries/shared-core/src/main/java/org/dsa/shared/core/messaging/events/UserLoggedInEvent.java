package org.dsa.shared.core.messaging.events;

import lombok.Builder;
import org.dsa.shared.core.messaging.contracts.AuthEvent;

@Builder
public record UserLoggedInEvent(Long accountId, String username, String email)
    implements AuthEvent {}
