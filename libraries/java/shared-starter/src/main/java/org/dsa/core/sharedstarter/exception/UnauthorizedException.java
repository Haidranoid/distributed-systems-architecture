package org.dsa.core.sharedstarter.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException() {
    super("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
