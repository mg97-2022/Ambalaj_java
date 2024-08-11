package com.Ambalaj.Ambalaj.interfaces;

import com.Ambalaj.Ambalaj.model.AppUserEntity;

import java.util.Optional;

public interface AppUserExtraDetails<T extends AppUserDetails> {
    Optional<T> findByAppUser(AppUserEntity appUser);
}