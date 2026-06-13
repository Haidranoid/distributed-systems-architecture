package org.dsa.services.authenticationservice.messaging.topics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopics {

    public static final String ACCOUNT_CREATED = "account-created";
}
