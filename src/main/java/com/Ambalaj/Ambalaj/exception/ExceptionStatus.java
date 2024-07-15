package com.Ambalaj.Ambalaj.exception;

public enum ExceptionStatus {
    FAIL("Fail"),
    ERROR("Error");

    private final String status;

    ExceptionStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}