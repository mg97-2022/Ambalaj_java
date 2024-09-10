package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer>, JpaSpecificationExecutor<CountryEntity> {
    @Query("SELECT DISTINCT c FROM CountryEntity c LEFT JOIN FETCH c.cities")
    List<CountryEntity> findAllCountriesWithCities();
}
