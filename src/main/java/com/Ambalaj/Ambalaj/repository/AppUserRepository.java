package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends CrudRepository<AppUserEntity, UUID> {
    Optional<AppUserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
