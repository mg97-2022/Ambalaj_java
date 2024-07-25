package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.exception.ErrorResponse;
import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.exception.ExceptionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException ex) {
            handleException(response, ex, ex.getStatusCode(), ex.getStatus());
        } catch (Exception ex) {
            handleException(response, ex, HttpStatus.INTERNAL_SERVER_ERROR, ExceptionStatus.ERROR.toString());
        }
    }

    private void handleException(@NotNull HttpServletResponse response, @NotNull Exception ex,
                                 @NotNull HttpStatus statusCode,
                                 String status) throws IOException {
        ErrorResponse errorResponse =
                ErrorResponse.builder().message(ex.getMessage()).status(status).error(statusCode.getReasonPhrase())
                        .path(ex.getStackTrace()[0].toString()).exception(ex.toString()).cause(ex.getCause()).build();

        response.setStatus(statusCode.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(convertObjectToJson(errorResponse));
        writer.flush();
        writer.close();
    }

    private String convertObjectToJson(ErrorResponse errorResponse) {
        try {
            return new ObjectMapper().writeValueAsString(errorResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
