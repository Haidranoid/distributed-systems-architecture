package org.dsa.services.auditservice.messaging.consumers;

import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.messaging.events.AccountCreatedEvent;
import org.dsa.core.sharedstarter.messaging.topics.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountCreatedConsumer {

    @KafkaListener(topics = KafkaTopics.ACCOUNT_CREATED)
    public void consume(AccountCreatedEvent event) {
        log.info("Received event: {}", event);
    }
}
