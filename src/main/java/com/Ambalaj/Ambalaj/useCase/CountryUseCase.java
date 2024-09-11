package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.util.List;

public interface CountryUseCase {
    CountryDTO addCountry(CountryDTO countryDTO);

    List<CountryDTO> getAllCountries();

    PaginatedDTO<CountryDTO> getCountries(
            Integer page, Integer pageSize, String sortBy, String sortDirection,
            String search);

    CountryDTO getCountry(Integer countryId);

    CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId);

    void deleteCountry(Integer countryId);
}
