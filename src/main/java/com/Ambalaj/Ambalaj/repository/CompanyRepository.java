package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByAppUser(AppUserEntity appUser);
}
