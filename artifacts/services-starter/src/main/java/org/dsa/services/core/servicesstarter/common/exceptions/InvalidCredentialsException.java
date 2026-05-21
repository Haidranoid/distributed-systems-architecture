package org.dsa.services.core.servicesstarter.common.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException() {
        super("Invalid credentials", HttpStatus.BAD_REQUEST);
    }
}