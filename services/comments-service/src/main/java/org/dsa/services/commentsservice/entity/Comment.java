package org.dsa.services.commentsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "comments")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String textContent;
}
