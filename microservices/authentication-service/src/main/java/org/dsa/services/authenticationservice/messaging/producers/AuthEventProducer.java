package org.dsa.services.authenticationservice.messaging.producers;

import org.dsa.shared.core.messaging.contracts.AuthEvent;
import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.dsa.shared.core.messaging.contracts.KafkaTopics;
import org.dsa.shared.core.messaging.producers.KafkaEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthEventProducer extends KafkaEventPublisher<AuthEvent> {

  public AuthEventProducer(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
    super(KafkaTopics.AUTHENTICATION_SERVICE_TOPIC, kafkaTemplate);
  }
}
