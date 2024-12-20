package com.Ambalaj.Ambalaj.utils;

public class Regex {
    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";

    public static final String URL_PATTERN = "^(https?|ftp):\\/\\/([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(\\/.*)?$";

    public static final String PHONE_NUMBER_PATTERN = "^[+]?[0-9]{10,15}$";

    public static final String COLOR_HEX_CODE_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8}|[A-Fa-f0-9]{3})$";
}
