package org.dsa.services.authenticationservice.messaging.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    // Spring Boot auto-wires this matching your serialized data types
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(String topic, String key, Object payload) {
        // Asynchronously sends payload to the desired topic partition
        this.kafkaTemplate.send(topic, key, payload);
    }
}
