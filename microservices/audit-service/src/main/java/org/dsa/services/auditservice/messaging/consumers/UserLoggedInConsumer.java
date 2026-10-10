package org.dsa.services.auditservice.messaging.consumers;

import lombok.RequiredArgsConstructor;
import org.dsa.services.auditservice.service.AuditService;
import org.dsa.shared.core.messaging.contracts.AccountEvent;
import org.dsa.shared.core.messaging.contracts.KafkaListenerTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoggedInConsumer {

  private final AuditService auditService;

  @KafkaListener(topics = KafkaListenerTopic.ACCOUNTS_SERVICE_TOPIC)
  public void consume(AccountEvent accountEvent) {
    auditService.register(accountEvent);
  }
}
