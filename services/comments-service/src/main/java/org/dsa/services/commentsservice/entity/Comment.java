package org.dsa.services.commentsservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "comments")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Comment {
  @Id
  @Setter(AccessLevel.NONE)
  private Long id;

  @NonNull
  @Column("text_content")
  private String textContent;
}
