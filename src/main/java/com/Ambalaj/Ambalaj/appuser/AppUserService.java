package com.Ambalaj.Ambalaj.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email %s not found.", email)));
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public void saveUser(AppUser user) {
        appUserRepository.save(user);
    }

    public boolean userExistsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }
}