package com.Ambalaj.Ambalaj.auth.confirmationToken;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken getConfirmationToken(String confirmationToken) throws NotFoundException {
        return confirmationTokenRepository.findByToken(confirmationToken)
                .orElseThrow(() -> new NotFoundException("Invalid confirmation token."));
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
}
