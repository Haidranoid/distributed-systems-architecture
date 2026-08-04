package org.dsa.shared.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
  public InvalidCredentialsException() {
    super("Invalid credentials", HttpStatus.BAD_REQUEST);
  }
}
