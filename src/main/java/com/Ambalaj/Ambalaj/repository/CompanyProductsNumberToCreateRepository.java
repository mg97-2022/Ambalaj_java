package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.model.CompanyProductsNumberToCreateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyProductsNumberToCreateRepository extends JpaRepository<CompanyProductsNumberToCreateEntity, Long> {
    Optional<CompanyProductsNumberToCreateEntity> findByCompany(CompanyEntity companyEntity);
}
