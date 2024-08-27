package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.repository.AppUserRepository;
import com.Ambalaj.Ambalaj.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUserEntity loadUserByUsername(String email) throws NotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User"));
    }

    @Override
    public void updateUser(AppUserEntity user) {
        appUserRepository.save(user);
    }

    @Override
    public AppUserEntity findUserByEmail(String email) throws NotFoundException {
        return loadUserByUsername(email);
    }

    @Override
    public AppUserEntity findUserByToken(String token) throws NotFoundException {
        return appUserRepository.findByToken(token).orElseThrow(() -> new NotFoundException("User"));
    }
}
