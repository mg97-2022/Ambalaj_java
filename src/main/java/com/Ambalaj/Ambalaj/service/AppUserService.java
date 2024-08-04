package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AppUserService extends UserDetailsService {
    Optional<AppUserEntity> findUserByEmail(String email);

    void saveUser(AppUserEntity user);

    boolean userExistsByEmail(String email);
}