package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.useCase.CountryUseCase;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryUseCase countryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<CountryDTO>> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        CountryDTO addedCountry = countryUseCase.addCountry(countryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.<CountryDTO>builder().data(addedCountry).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CountryDTO>>> getCountries() {
        List<CountryDTO> countries = countryUseCase.getCountryList();
        return ResponseEntity.ok(ResponseDTO.<List<CountryDTO>>builder().data(countries).build());
    }

    @GetMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO<CountryDTO>> getCountryDetails(@PathVariable Integer countryId) {
        CountryDTO country = countryUseCase.getCountry(countryId);
        return ResponseEntity.ok(ResponseDTO.<CountryDTO>builder().data(country).build());
    }

    @PutMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO<CountryDTO>> updateCountry(@Valid @RequestBody CountryDTO countryDTO,
                                                                 @PathVariable Integer countryId) {
        CountryDTO updatedCountry = countryUseCase.updateCountry(countryDTO, countryId);
        return ResponseEntity.ok(ResponseDTO.<CountryDTO>builder().data(updatedCountry).build());
    }

    @DeleteMapping(path = "/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer countryId) {
        countryUseCase.deleteCountry(countryId);
        return ResponseEntity.noContent().build();
    }
}
