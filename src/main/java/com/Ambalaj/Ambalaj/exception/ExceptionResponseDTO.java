package com.Ambalaj.Ambalaj.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponseDTO {
    private final Date timestamp = new Date();
    private final Object message;
    private final String status;
    private final String error;
    private final String path;
    private final String exception;
    private final Throwable cause;
}