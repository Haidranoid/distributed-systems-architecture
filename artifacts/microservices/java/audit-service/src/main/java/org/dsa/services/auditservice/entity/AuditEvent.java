package org.dsa.services.auditservice.entity;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "audit_events")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AuditEvent {

  @Id @GeneratedValue private Long id;

  @NonNull
  @Column(nullable = false, updatable = false, length = 100)
  private String eventType;

  @NonNull
  @Column(nullable = false, updatable = false)
  private Instant occurredAt;

  @NonNull
  @Column(nullable = false, updatable = false)
  // @JdbcTypeCode(SqlTypes.JSON)
  private String payload;
}
