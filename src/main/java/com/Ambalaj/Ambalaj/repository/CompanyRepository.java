package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetails;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>, AppUserExtraDetails<CompanyEntity> {
}
