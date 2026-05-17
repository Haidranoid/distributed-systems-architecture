package org.dsa.services.authenticationservice.common.entities;

import lombok.experimental.SuperBuilder;
import org.dsa.services.authenticationservice.common.constants.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tokens")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TokenEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 1000)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    private Long accountId;
}
