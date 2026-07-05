package org.dsa.services.auditservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.messaging.contracts.KafkaEvent;
import org.dsa.services.auditservice.common.entities.AuditEventEntity;
import org.dsa.services.auditservice.mappers.AuditMapper;
import org.dsa.services.auditservice.repositories.AuditRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public void register(KafkaEvent event){

        AuditEventEntity entity = auditMapper.toEntity(event);

        auditRepository.save(entity);

        log.info("Received event: {}", event);
    }
}
