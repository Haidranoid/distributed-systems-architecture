package org.dsa.services.auditservice.repositories;

import org.dsa.services.auditservice.common.entities.AuditEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditEventEntity, Long> {
}
