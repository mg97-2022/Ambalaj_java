package com.Ambalaj.Ambalaj.exception;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Profile("prod")
@ControllerAdvice
public class ProductionGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCustomException(CustomException ex) {
        ExceptionResponseDTO errorResponse = ExceptionResponseDTO.builder()
                .message(ex.getMessage())
                .status(ex.getStatus())
                .build();

        HttpStatus status = ex.getStatusCode();

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex) {
        ExceptionResponseDTO errorResponse = ExceptionResponseDTO.builder()
                .message("An unexpected error occurred")
                .status(ExceptionStatus.ERROR.toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
