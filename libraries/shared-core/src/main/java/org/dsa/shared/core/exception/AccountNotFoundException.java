package org.dsa.shared.core.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
  public AccountNotFoundException(String message) {
    super("Account not found: " + message, HttpStatus.NOT_FOUND);
  }
}
