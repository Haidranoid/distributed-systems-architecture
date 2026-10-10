package org.dsa.shared.core.messaging.contracts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaListenerTopic {
  public static final String AUTHENTICATION_SERVICE_TOPIC = "authentication_service_topic";
  public static final String ACCOUNTS_SERVICE_TOPIC = "accounts_service_topic";
}
