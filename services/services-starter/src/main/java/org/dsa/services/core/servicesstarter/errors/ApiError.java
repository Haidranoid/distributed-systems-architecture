package org.dsa.services.core.servicesstarter.errors;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private final String message;
    private final LocalDateTime timestamp;
}
