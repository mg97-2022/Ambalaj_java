package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetails;
import com.Ambalaj.Ambalaj.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>, AppUserExtraDetails<AdminEntity> {
}
