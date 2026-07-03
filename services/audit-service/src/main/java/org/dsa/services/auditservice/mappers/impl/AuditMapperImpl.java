package org.dsa.services.auditservice.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;
import org.dsa.services.auditservice.common.entities.AuditEventEntity;
import org.dsa.services.auditservice.mappers.AuditMapper;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuditMapperImpl implements AuditMapper {

    private final ObjectMapper objectMapper;

    @Override
    public AuditEventEntity toEntity(KafkaEvent event) {
        try {
            return AuditEventEntity.builder()
                    .eventType(event.getClass().getSimpleName())
                    .occurredAt(Instant.now())
                    .payload(objectMapper.writeValueAsString(event))
                    .build();
        } catch (JacksonException e) {
            throw new IllegalStateException("Unable to serialize audit event", e);
        }
    }
}