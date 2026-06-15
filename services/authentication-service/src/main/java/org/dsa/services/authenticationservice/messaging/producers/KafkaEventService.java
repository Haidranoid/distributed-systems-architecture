package org.dsa.services.authenticationservice.messaging.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.authenticationservice.messaging.topics.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvent(
            KafkaTopics topic,
            String key,
            Object payload
    ) {
        log.info("Publishing event to {}", topic.getTopic());

        kafkaTemplate.send(
                topic.getTopic(),
                key,
                payload
        );

        log.info("Publish invoked");
    }
}
