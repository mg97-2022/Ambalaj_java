package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CountryEntity;
import org.springframework.data.domain.Page;

public interface CountryService {
    CountryEntity addCountry(CountryEntity countryEntity);

    Page<CountryEntity> getCountries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                     String search);

    CountryEntity getCountry(Integer countryId);

    CountryEntity updateCountry(CountryEntity countryEntity, Integer countryId);

    void deleteCountry(Integer countryId);
}
