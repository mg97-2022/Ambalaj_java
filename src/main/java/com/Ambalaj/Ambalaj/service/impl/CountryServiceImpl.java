package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.repository.CountryRepository;
import com.Ambalaj.Ambalaj.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public CountryEntity addCountry(CountryEntity country) {
        return countryRepository.save(country);
    }

    @Override
    public List<CountryEntity> getCountryList() {
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity getCountry(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found with ID: " + countryId));
    }

    @Override
    public CountryEntity updateCountry(CountryEntity countryEntity, Integer countryId) {
        CountryEntity country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found with ID: " + countryId));
        country.setName(countryEntity.getName());
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Integer countryId) {
        boolean isExists = countryRepository.existsById(countryId);
        if (!isExists) throw new NotFoundException("Country not found with ID: " + countryId);
        countryRepository.deleteById(countryId);
    }
}
