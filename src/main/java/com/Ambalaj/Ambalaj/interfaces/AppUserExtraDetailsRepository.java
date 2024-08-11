package com.Ambalaj.Ambalaj.interfaces;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserExtraDetailsRepository<T extends AppUserDetails> extends JpaRepository<T, Long> {
    Optional<T> findByAppUser(AppUserEntity appUser);
}
