package com.Ambalaj.Ambalaj.exception;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends CustomException {
    public InvalidDataException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
