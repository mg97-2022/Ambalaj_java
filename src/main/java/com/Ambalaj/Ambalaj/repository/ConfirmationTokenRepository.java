package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.ConfirmationTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenEntity, Long> {
    Optional<ConfirmationTokenEntity> findByToken(String token);
}
