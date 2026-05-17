package org.dsa.services.authenticationservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.dsa.services.core.servicesstarter.errors.ApiError;
import org.dsa.services.core.servicesstarter.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex) {

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(
                        ApiError.builder()
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(Exception ex) {

        log.error("Unexpected exception", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiError.builder()
                                .message("Unexpected server error")
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}