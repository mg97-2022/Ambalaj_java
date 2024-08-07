package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CityEntity;
import com.Ambalaj.Ambalaj.repository.CityRepository;
import com.Ambalaj.Ambalaj.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityEntity getCity(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException("City not found with ID: " + cityId));
    }
}
