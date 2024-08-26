package com.Ambalaj.Ambalaj.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(String itemName) {
        super(itemName + " not found", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String itemName, Object id) {
        super(itemName + " not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
}
