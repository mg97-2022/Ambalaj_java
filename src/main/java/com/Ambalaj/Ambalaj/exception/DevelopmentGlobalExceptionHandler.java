package com.Ambalaj.Ambalaj.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@Profile("dev")
@ControllerAdvice
public class DevelopmentGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        HttpStatus statusCode = ex.getStatusCode();

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getStatus(),
                statusCode.getReasonPhrase(),
                ex.getStackTrace()[0].toString(),
                ex.toString(),
                ex.getCause()
        );

        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ExceptionStatus.ERROR.toString(),
                statusCode.getReasonPhrase(),
                ex.getStackTrace()[0].toString(),
                ex.toString(),
                ex.getCause()
        );

        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
