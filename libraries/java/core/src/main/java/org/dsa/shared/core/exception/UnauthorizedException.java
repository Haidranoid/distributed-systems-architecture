package org.dsa.shared.core.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException() {
    super("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
