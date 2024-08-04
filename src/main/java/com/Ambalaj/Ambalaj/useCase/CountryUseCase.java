package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CountryDTO;

import java.util.List;

public interface CountryUseCase {
    CountryDTO addCountry(CountryDTO countryDTO);

    List<CountryDTO> getCountryList();

    CountryDTO getCountry(Integer countryId);

    CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId);

    void deleteCountry(Integer countryId);
}
