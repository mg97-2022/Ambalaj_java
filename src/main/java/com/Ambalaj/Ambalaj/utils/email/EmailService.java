package com.Ambalaj.Ambalaj.utils.email;

import com.Ambalaj.Ambalaj.exception.CustomException;

public interface EmailService {
    public void send(String to, String emailTemplate, String subject) throws CustomException;
}
