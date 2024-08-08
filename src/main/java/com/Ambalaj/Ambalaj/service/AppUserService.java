package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserService extends UserDetailsService {
    @Override
    AppUserEntity loadUserByUsername(String email) throws UsernameNotFoundException;

    void updateUser(AppUserEntity user);

    AppUserEntity findUserByEmail(String email) throws UsernameNotFoundException;

    AppUserEntity findUserByResetPasswordToken(String resetPasswordToken) throws NotFoundException;

    boolean userExistsByEmail(String email);
}