package org.dsa.services.accountsservice.controller.advice;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.error.ApiError;
import org.dsa.core.sharedstarter.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ApiError> handleBaseException(BaseException ex) {

    return ResponseEntity.status(ex.getHttpStatus())
        .body(ApiError.builder().message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUnexpected(Exception ex) {

    log.error("Unexpected exception", ex);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ApiError.builder()
                .message("Unexpected server error")
                .timestamp(LocalDateTime.now())
                .build());
  }
}
