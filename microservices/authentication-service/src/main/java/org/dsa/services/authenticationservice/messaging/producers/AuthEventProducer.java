package org.dsa.services.authenticationservice.messaging.producers;

import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.dsa.shared.core.messaging.contracts.KafkaProducerTopic;
import org.dsa.shared.core.messaging.events.AuthEvent;
import org.dsa.shared.core.messaging.producers.KafkaEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthEventProducer extends KafkaEventPublisher<AuthEvent> {

  public AuthEventProducer(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
    super(KafkaProducerTopic.AUTHENTICATION_SERVICE_TOPIC, kafkaTemplate);
  }

  @Override
  protected String generateEventKey(AuthEvent event) {
    return event.accountId().toString();
  }
}
