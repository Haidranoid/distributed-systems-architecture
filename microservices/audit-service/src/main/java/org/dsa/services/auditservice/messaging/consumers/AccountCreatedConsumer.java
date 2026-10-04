package org.dsa.services.auditservice.messaging.consumers;

import lombok.RequiredArgsConstructor;
import org.dsa.services.auditservice.service.AuditService;
import org.dsa.shared.core.messaging.contracts.AuthEvent;
import org.dsa.shared.core.messaging.contracts.KafkaListenerTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountCreatedConsumer {

  private final AuditService auditService;

  @KafkaListener(topics = KafkaListenerTopics.AUTHENTICATION_SERVICE_TOPIC)
  public void consume(AuthEvent authEvent) {
    auditService.register(authEvent);
  }
}
