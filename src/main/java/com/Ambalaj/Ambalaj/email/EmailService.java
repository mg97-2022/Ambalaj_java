package com.Ambalaj.Ambalaj.email;

import com.Ambalaj.Ambalaj.exception.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void send(String to, String emailTemplate, String subject) throws CustomException {
        final String EMAIL_SENDER = "mg97@gmail.com";

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(emailTemplate, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(EMAIL_SENDER);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("failed to send email: ", e);
            throw new CustomException("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
