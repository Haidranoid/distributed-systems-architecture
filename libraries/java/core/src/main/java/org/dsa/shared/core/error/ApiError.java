package org.dsa.shared.core.error;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
  private final String message;
  private final LocalDateTime timestamp;
}
