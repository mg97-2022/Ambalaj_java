package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.CityUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/city")
@Validated
public class CityController {
    private final CityUseCase cityUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<CityDTO>> addCity(@Valid @RequestBody CityDTO cityDTO) {
        CityDTO addedCity = cityUseCase.addCity(cityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.<CityDTO>builder().data(addedCity).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<CityDTO>>> getCities(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<CityDTO> cities = cityUseCase.getCities(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<CityDTO>>builder().data(cities).build());
    }

    @GetMapping(path = "/{cityId}")
    public ResponseEntity<ResponseDTO<CityDTO>> getCity(@PathVariable Long cityId) {
        CityDTO city = cityUseCase.getCity(cityId);
        return ResponseEntity.ok(ResponseDTO.<CityDTO>builder().data(city).build());
    }

    @PutMapping(path = "/{cityId}")
    public ResponseEntity<ResponseDTO<CityDTO>> updateCity(@Valid @RequestBody CityDTO cityDto,
                                                           @PathVariable Long cityId) {
        CityDTO updatedCity = cityUseCase.updateCity(cityDto, cityId);
        return ResponseEntity.ok(ResponseDTO.<CityDTO>builder().data(updatedCity).build());
    }

    @DeleteMapping(path = "/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        cityUseCase.deleteCity(cityId);
        return ResponseEntity.noContent().build();
    }
}
