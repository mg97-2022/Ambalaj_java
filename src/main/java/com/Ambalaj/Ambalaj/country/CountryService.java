package com.Ambalaj.Ambalaj.country;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public Country addCountry(CountryDTO countryDTO) {
        var country = Country.builder().name(countryDTO.getName()).build();
        return countryRepository.save(country);
    }

    public Iterable<Country> getCountryList() {
        return countryRepository.findAll();
    }

    public Country getCountry(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found with ID: " + countryId));
    }

    public Country updateCountry(CountryDTO countryDTO, Integer countryId) {
        var country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found with ID: " + countryId));
        country.setName(countryDTO.getName());
        return countryRepository.save(country);
    }

    public void deleteCountry(Integer countryId) {
        boolean isExists = countryRepository.existsById(countryId);
        if (!isExists) throw new NotFoundException("Country not found with ID: " + countryId);
        countryRepository.deleteById(countryId);
    }
}
