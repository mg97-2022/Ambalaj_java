package com.Ambalaj.Ambalaj.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String status;

    private String getStatus(HttpStatus statusCode) {
        return String.valueOf(statusCode.value()).startsWith("4") ?
                ExceptionStatus.FAIL.toString() :
                ExceptionStatus.ERROR.toString();
    }

    public CustomException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.status = getStatus(statusCode);
    }

    public CustomException(String message, HttpStatus statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.status = getStatus(statusCode);
    }
}