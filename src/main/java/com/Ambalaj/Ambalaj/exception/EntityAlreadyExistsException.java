package com.Ambalaj.Ambalaj.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends CustomException {
    public EntityAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
