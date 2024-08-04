package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
    ConfirmationTokenEntity getConfirmationToken(String confirmationToken) throws NotFoundException;

    void saveConfirmationToken(ConfirmationTokenEntity confirmationToken);
}
