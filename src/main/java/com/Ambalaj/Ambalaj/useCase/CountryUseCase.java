package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryUseCase {
    CountryDTO addCountry(CountryDTO countryDTO);

    PaginatedDTO<CountryDTO> getCountries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                            String search);

    CountryDTO getCountry(Integer countryId);

    CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId);

    void deleteCountry(Integer countryId);
}
