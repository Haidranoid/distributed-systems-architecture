package org.dsa.services.commentsservice.dto.response;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record CommentResponse(Long id, String textContent) {
  @Override
  public @NonNull String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("textContent", textContent)
        .toString();
  }
}
