package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, UUID> {
    Optional<AppUserEntity> findByEmail(String email);

    Optional<AppUserEntity> findByToken(String token);
}
