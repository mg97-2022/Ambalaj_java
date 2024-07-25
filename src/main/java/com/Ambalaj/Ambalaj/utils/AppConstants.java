package com.Ambalaj.Ambalaj.utils;

public class AppConstants {
    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
    public static final String PASSWORD_ERROR_MESSAGE =
            "must include a mix of uppercase and lowercase letters, numbers, and special characters.";
}
