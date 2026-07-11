package org.dsa.core.sharedstarter.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
  public AccountNotFoundException(String message) {
    super("Account not found: " + message, HttpStatus.NOT_FOUND);
  }
}
