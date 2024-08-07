package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Long> {
}
