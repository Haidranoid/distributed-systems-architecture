package org.dsa.shared.core.messaging.contracts;

public enum KafkaProducerTopic {
  AUTHENTICATION_SERVICE_TOPIC,
  ACCOUNTS_SERVICE_TOPIC;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
