package org.dsa.shared.starter.legacy.producers.messaging;


// import org.springframework.kafka.core.KafkaTemplate;

// @Slf4j
// @RequiredArgsConstructor
public class LEGACY_KafkaEventPublisher {
  /*
  private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  public void publish(String topic, String key, KafkaEvent event) {

    var eventName = event.getClass().getSimpleName();

    log.info("Publishing in topic={}: event={} with key={}", topic, eventName, key);

    kafkaTemplate.send(topic, key, event);
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

  // log.info("Publish completed in topic={}: event={} with key={}", topic, eventName, key);
  // }*/
}
