package org.dsa.services.auditservice.mappers;

import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;
import org.dsa.services.auditservice.common.entities.AuditEventEntity;

public interface AuditMapper {
    AuditEventEntity toEntity(KafkaEvent event);
}
