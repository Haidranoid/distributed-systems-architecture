package org.dsa.core.sharedstarter.error;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
  private final String message;
  private final LocalDateTime timestamp;
}
