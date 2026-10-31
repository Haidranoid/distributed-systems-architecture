package org.dsa.services.authenticationservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.dsa.services.authenticationservice.constant.TokenType;

@Table(name = "tokens")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Token {
  @Id @GeneratedValue private Long id;

  @Column(unique = true, length = 1000)
  private String token;

  @Enumerated(EnumType.STRING)
  private TokenType tokenType;

  private boolean expired;

  private boolean revoked;

  private Long accountId;
}
