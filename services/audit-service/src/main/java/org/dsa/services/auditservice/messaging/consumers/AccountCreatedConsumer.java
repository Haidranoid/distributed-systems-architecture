package org.dsa.services.auditservice.messaging.consumers;

import lombok.RequiredArgsConstructor;
import org.dsa.core.sharedstarter.messaging.events.AccountCreatedEvent;
import org.dsa.core.sharedstarter.messaging.topics.KafkaTopics;
import org.dsa.services.auditservice.service.AuditService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountCreatedConsumer {

  private final AuditService auditService;

  @KafkaListener(topics = KafkaTopics.ACCOUNT_CREATED)
  public void consume(AccountCreatedEvent accountCreatedEvent) {
    auditService.register(accountCreatedEvent);
  }
}
