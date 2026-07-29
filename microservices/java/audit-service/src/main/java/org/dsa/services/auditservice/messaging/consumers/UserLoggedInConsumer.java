package org.dsa.services.auditservice.messaging.consumers;

import lombok.RequiredArgsConstructor;
import org.dsa.shared.core.messaging.events.UserLoggedInEvent;
import org.dsa.shared.core.messaging.topics.KafkaTopics;
import org.dsa.services.auditservice.service.AuditService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoggedInConsumer {

  private final AuditService auditService;

  @KafkaListener(topics = KafkaTopics.USER_LOGGED_IN)
  public void consume(UserLoggedInEvent userLoggedInEvent) {
    auditService.register(userLoggedInEvent);
  }
}
