package org.dsa.services.authenticationservice.messaging.topics;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum KafkTopics {

    ACCOUNT_CREATED("account-created");
}
