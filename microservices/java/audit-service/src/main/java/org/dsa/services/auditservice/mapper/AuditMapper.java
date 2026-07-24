package org.dsa.services.auditservice.mapper;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;
import org.dsa.services.auditservice.entity.AuditEvent;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class AuditMapper {

  private final ObjectMapper objectMapper;

  public AuditEvent toEntity(KafkaEvent event) {
    try {
      return AuditEvent.builder()
          .eventType(event.getClass().getSimpleName())
          .occurredAt(Instant.now())
          .payload(objectMapper.writeValueAsString(event))
          .build();
    } catch (JacksonException e) {
      throw new IllegalStateException("Unable to serialize audit event", e);
    }
  }
}
