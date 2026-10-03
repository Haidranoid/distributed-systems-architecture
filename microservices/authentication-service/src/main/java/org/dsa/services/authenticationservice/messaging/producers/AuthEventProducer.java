package org.dsa.services.authenticationservice.messaging.producers;

import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.dsa.shared.core.messaging.contracts.KafkaTopics;
import org.dsa.shared.core.messaging.producers.KafkaEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthEventProducer extends KafkaEventPublisher {

  public AuthEventProducer(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
    super(kafkaTemplate, KafkaTopics.AUTHENTICATION_SERVICE_TOPIC);
  }
}
