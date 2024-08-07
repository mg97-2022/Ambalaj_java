package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AppUserService extends UserDetailsService {
    @Override
    AppUserEntity loadUserByUsername(String email) throws UsernameNotFoundException;

    void updateUser(AppUserEntity user);

    Optional<AppUserEntity> findUserByEmail(String email);

    void saveUser(AppUserEntity user);

    boolean userExistsByEmail(String email);
}