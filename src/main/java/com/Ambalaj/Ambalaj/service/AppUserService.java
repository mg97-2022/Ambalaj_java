package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    @Override
    AppUserEntity loadUserByUsername(String email) throws NotFoundException;

    void updateUser(AppUserEntity user);

    AppUserEntity findUserByEmail(String email) throws NotFoundException;

    AppUserEntity findUserByToken(String token) throws NotFoundException;
}