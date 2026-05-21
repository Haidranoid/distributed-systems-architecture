package org.dsa.core.sharedstarter.common.exceptions;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(String message) {
        super("Account not found: " + message, HttpStatus.NOT_FOUND);
    }
}
