package org.dsa.services.auditservice.repository;

import org.dsa.services.auditservice.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditEvent, Long> {}
