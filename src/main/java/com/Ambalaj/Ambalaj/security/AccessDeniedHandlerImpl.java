package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.exception.ExceptionResponseDTO;
import com.Ambalaj.Ambalaj.exception.ExceptionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws
            IOException, ServletException {

        ExceptionResponseDTO exceptionResponse = ExceptionResponseDTO.builder()
                .message("You do not have the necessary permissions to access this resource.")
                .status(ExceptionStatus.FAIL.toString()).error(HttpStatus.FORBIDDEN.getReasonPhrase()).build();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        var responseBody = objectMapper.writeValueAsString(exceptionResponse);
        writer.write(responseBody);
        writer.flush();
        writer.close();
    }
}
