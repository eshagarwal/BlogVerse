package org.gisma.blogverse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid date provided")
public class InvalidDateProvidedException extends RuntimeException {
    public InvalidDateProvidedException(String message) {
        super(message);
    }
}
