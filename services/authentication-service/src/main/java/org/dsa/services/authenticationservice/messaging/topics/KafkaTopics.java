package org.dsa.services.authenticationservice.messaging.topics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KafkaTopics {

    ACCOUNT_CREATED("account-created"),
    ACCOUNT_UPDATED("account-updated"),
    PASSWORD_CHANGED("password-changed");

    private final String topic;
}
