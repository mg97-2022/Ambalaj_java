package com.Ambalaj.Ambalaj.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    private final Date timestamp = new Date();
    private final String message;
    private final String status;
    private final String error;
    private final String path;
    private final String exception;
    private final Throwable cause;

    // Constructors
    public ErrorResponse(String message, String status, String error, String path, String exception, Throwable cause) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
        this.exception = exception;
        this.cause = cause;
    }

    public ErrorResponse(String message, String status) {
        this(message, status, null, null, null, null);
    }
}

