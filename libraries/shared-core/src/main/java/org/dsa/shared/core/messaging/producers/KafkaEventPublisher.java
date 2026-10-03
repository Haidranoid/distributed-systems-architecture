package org.dsa.shared.core.messaging.producers;

import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.messaging.contracts.KafkaTopics;
import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public abstract class KafkaEventPublisher {

  private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
  private final KafkaTopics topic;

  public void publish(String eventKey, KafkaEvent event) {
    kafkaTemplate.send(topic.toString(), eventKey, event);
  }
}
