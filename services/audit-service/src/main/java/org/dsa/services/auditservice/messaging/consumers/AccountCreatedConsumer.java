package org.dsa.services.auditservice.messaging.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.messaging.events.AccountCreatedEvent;
import org.dsa.core.sharedstarter.messaging.topics.KafkaTopics;
import org.dsa.services.auditservice.services.AuditService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountCreatedConsumer {

    private final AuditService auditService;

    @KafkaListener(topics = KafkaTopics.ACCOUNT_CREATED)
    public void consume(AccountCreatedEvent accountCreatedEvent) {
        log.info("Received event: {}", accountCreatedEvent);

        auditService.register(accountCreatedEvent);
    }
}
