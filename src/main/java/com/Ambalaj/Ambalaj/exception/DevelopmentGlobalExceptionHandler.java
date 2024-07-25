package com.Ambalaj.Ambalaj.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@ControllerAdvice
@RequiredArgsConstructor
public class DevelopmentGlobalExceptionHandler {
    private final ExceptionUtils exceptionUtils;

    // Handles request missing body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = ErrorResponse.builder().message(
                        "Request body is missing or not readable. Please ensure that the request body is properly formatted.")
                .status(ExceptionStatus.FAIL.toString()).error(statusCode.getReasonPhrase())
                .path(ex.getStackTrace()[0].toString()).exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(errorResponse, statusCode);
    }

    // Handles validation errors messages for annotations (@Valid or @Validated)
    // For the response message, it will be an array of messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });

        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse =
                ErrorResponse.builder().message(errorMessages).status(ExceptionStatus.FAIL.toString())
                        .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                        .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(errorResponse, statusCode);
    }

    // Handles duplicate violation constraints that are thrown from db
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = exceptionUtils.getDataIntegrityViolationMessage(ex.getMostSpecificCause().getMessage());
        HttpStatus statusCode = HttpStatus.CONFLICT;

        ErrorResponse errorResponse =
                ErrorResponse.builder().message(errorMessage).status(ExceptionStatus.FAIL.toString())
                        .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                        .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(errorResponse, statusCode);
    }

    // Handles my custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        HttpStatus statusCode = ex.getStatusCode();

        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage()).status(ex.getStatus())
                .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString()).exception(ex.toString())
                .cause(ex.getCause()).build();

        return new ResponseEntity<>(errorResponse, statusCode);
    }

    // If the exception is not of the above exception types, then this will be returned
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse =
                ErrorResponse.builder().message(ex.getMessage()).status(ExceptionStatus.ERROR.toString())
                        .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                        .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
