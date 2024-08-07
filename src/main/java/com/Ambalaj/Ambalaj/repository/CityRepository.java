package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
