package com.Ambalaj.Ambalaj.exception;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExceptionUtils {
    public String getDataIntegrityViolationMessage(String errorMessage) {
        final String ERROR_MESSAGE_TEMPLATE = "%s [%s] already exists.";
        // Regular expression to match the part of the message you need
        final String REGEX_PATTERN = "Key \\(([^)]+)\\)=\\(([^)]+)\\) already exists";
        // Pattern and Matcher to find the match
        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            String field = matcher.group(1);
            String value = matcher.group(2);
            return String.format(ERROR_MESSAGE_TEMPLATE, field, value);
        } else {
            return "Invalid data.";
        }
    }
}
