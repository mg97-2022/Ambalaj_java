package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetails;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>, AppUserExtraDetails<CompanyEntity>, JpaSpecificationExecutor<CompanyEntity> {
    @Query("SELECT c FROM CompanyEntity c JOIN FETCH c.appUser WHERE c.name = :companyName")
    CompanyEntity findByName(String companyName);
}
