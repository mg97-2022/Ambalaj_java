package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.repository.AppUserRepository;
import com.Ambalaj.Ambalaj.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email %s not found.", email)));
    }

    @Override
    public void updateUser(AppUserEntity user) {
        appUserRepository.save(user);
    }

    @Override
    public Optional<AppUserEntity> findUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public void saveUser(AppUserEntity user) {
        appUserRepository.save(user);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }
}
