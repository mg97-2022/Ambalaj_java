package com.Ambalaj.Ambalaj.exception;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExceptionUtils {
    private String duplicateMatcher(String errorMessage) {
        final String REGEX_PATTERN = "Key \\(([^)]+)\\)=\\(([^)]+)\\) already exists";
        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        Matcher duplicateMatcher = pattern.matcher(errorMessage);
        if (duplicateMatcher.find()) {
            String field = duplicateMatcher.group(1);
            String value = duplicateMatcher.group(2);
            return String.format("%s [%s] already exists.", field, value);
        }
        return null;
    }

    private String nullValueMatcher(String errorMessage) {
        final String NULL_VIOLATION_PATTERN =
                "null value in column \"([^\"]+)\" of relation \"[^\"]+\" violates not-null constraint";

        Pattern nullViolationPattern = Pattern.compile(NULL_VIOLATION_PATTERN);
        Matcher nullViolationMatcher = nullViolationPattern.matcher(errorMessage);
        if (nullViolationMatcher.find()) {
            String columnName = nullViolationMatcher.group(1);
            return String.format("The field [%s] cannot be null.", columnName);
        }
        return null;
    }

    public String getDataIntegrityViolationMessage(String errorMessage) {
        String duplicateString = duplicateMatcher(errorMessage);
        String nullValueString = nullValueMatcher(errorMessage);

        if (duplicateString != null) return duplicateString;
        if (nullValueString != null) return nullValueString;
        return "Invalid data";
    }
}
