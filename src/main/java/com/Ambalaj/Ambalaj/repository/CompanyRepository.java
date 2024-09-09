package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetails;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>, AppUserExtraDetails<CompanyEntity> {
    CompanyEntity findByName(String companyName);
}
