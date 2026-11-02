package org.dsa.services.commentsservice.dto.request;

import com.google.common.base.MoreObjects;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record CreateCommentRequest(
        @NotBlank String textContent
) {
    @Override
    public @NonNull String toString() {
        return MoreObjects.toStringHelper(this)
                .add("textContent", textContent)
                .toString();
    }
}
