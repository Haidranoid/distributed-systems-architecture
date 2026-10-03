package org.dsa.shared.core.messaging.producers;

import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.dsa.shared.core.messaging.contracts.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public abstract class KafkaEventPublisher<T extends KafkaEvent> {

  private final KafkaTopics topic;
  private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  public void publish(T event) {
    kafkaTemplate.send(topic.toString(), event.getEventKey(), event);
  }
}
