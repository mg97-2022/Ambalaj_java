package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CountryEntity;

import java.util.List;

public interface CountryService {

    CountryEntity addCountry(CountryEntity countryEntity);

    List<CountryEntity> getCountryList();

    CountryEntity getCountry(Integer countryId);

    CountryEntity updateCountry(CountryEntity countryEntity, Integer countryId);

    void deleteCountry(Integer countryId);
}
