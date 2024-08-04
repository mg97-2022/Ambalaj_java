package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.ConfirmationTokenEntity;
import com.Ambalaj.Ambalaj.repository.ConfirmationTokenRepository;
import com.Ambalaj.Ambalaj.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationTokenEntity getConfirmationToken(String confirmationToken) throws NotFoundException {
        return confirmationTokenRepository.findByToken(confirmationToken)
                .orElseThrow(() -> new NotFoundException("Invalid confirmation token."));
    }

    @Override
    public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
}
