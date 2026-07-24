package org.dsa.core.sharedstarter.messaging.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class KafkaEventPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void publishEvent(String topic, String key, KafkaEvent payload) {
    log.info(
        "Publishing: event={}, topic={}, key={}", payload.getClass().getSimpleName(), topic, key);

    kafkaTemplate.send(topic, key, payload);
    /*.whenComplete((result, ex) -> {

        if (ex != null) {
            log.error("Kafka publish failed", ex);
            return;
        }

        var metadata = result.getRecordMetadata();

        log.info(
                "Kafka publish succeeded: topic={}, partition={}, offset={}",
                metadata.topic(),
                metadata.partition(),
                metadata.offset()
        );
    });*/

    log.info("Kafka publish completed: topic={}", topic);
  }
}
