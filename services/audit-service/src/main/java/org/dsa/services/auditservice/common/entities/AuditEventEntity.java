package org.dsa.services.auditservice.common.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Table(name = "audit_events")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AuditEventEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, updatable = false, length = 100)
    private String eventType;

    @NonNull
    @Column(nullable = false, updatable = false)
    private Instant occurredAt;

    @NonNull
    @Column(nullable = false, updatable = false)
    //@JdbcTypeCode(SqlTypes.JSON)
    private String payload;
}

