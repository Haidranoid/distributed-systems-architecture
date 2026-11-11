package org.dsa.services.commentsservice.exception.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
  private final String message;
  private final LocalDateTime timestamp;
}
