package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetailsRepository;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends AppUserExtraDetailsRepository<CompanyEntity> {
}
