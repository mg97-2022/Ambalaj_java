package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.interfaces.AppUserDetails;
import com.Ambalaj.Ambalaj.model.AppUserEntity;

public interface AppUserExtraDetailsService<T extends AppUserDetails> {
    T findByAppUser(AppUserEntity appUser) throws NotFoundException;
}
