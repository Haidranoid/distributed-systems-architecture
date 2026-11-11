package org.dsa.services.commentsservice.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.exception.CommentNotFoundException;
import org.dsa.services.commentsservice.exception.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<ApiError> handleCommentNotFoundException(CommentNotFoundException ex) {
    // return ResponseEntity.status(ex.getHttpStatus())
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
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
