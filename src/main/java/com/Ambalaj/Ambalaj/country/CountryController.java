package com.Ambalaj.Ambalaj.country;

import com.Ambalaj.Ambalaj.exception.EntityAlreadyExistsException;
import com.Ambalaj.Ambalaj.exception.ExceptionResponseDTO;
import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.utils.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    // SHOULD I CREATE DTO FOR REQUEST BODY AND ANOTHER ONE FOR RESPONSE??
    // IN SWAGGER DOCUMENTATION, SHOULD THE SCHEMA BE COUNTRY CLASS INSTEAD OF ResponseDTO??
    @PostMapping
    @Operation(summary = "Add new country", description = "Admin adds a new country and sends it back after adding it.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Country created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Country already exists.",
                    content = @Content(schema = @Schema(implementation = EntityAlreadyExistsException.class)))})
    public ResponseEntity<ResponseDTO> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        Country newCountry = countryService.addCountry(countryDTO);
        var response = ResponseDTO.builder().data(newCountry).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all countries", description = "Get All countries list.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Countries list.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)))})
    public ResponseEntity<ResponseDTO> getCountries() {
        Iterable<Country> countries = countryService.getCountryList();
        var response = ResponseDTO.builder().data(countries).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{countryId}")
    @Operation(summary = "Get country details", description = "Get one country details by it's ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Country details.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invalid country ID.",
                    content = @Content(schema = @Schema(implementation = NotFoundException.class)))})
    public ResponseEntity<ResponseDTO> getCountryDetails(@PathVariable Integer countryId) {
        Country country = countryService.getCountry(countryId);
        var response = ResponseDTO.builder().data(country).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{countryId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated country details.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invalid country ID.",
                    content = @Content(schema = @Schema(implementation = NotFoundException.class)))})
    public ResponseEntity<ResponseDTO> updateCountry(@Valid @RequestBody CountryDTO countryDTO,
                                                     @PathVariable Integer countryId) {
        Country country = countryService.updateCountry(countryDTO, countryId);
        var response = ResponseDTO.builder().data(country).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{countryId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Country deleted Successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invalid country ID.",
                    content = @Content(schema = @Schema(implementation = NotFoundException.class)))})
    public ResponseEntity<ResponseDTO> deleteCountry(@PathVariable Integer countryId) {
        countryService.deleteCountry(countryId);
        var response = ResponseDTO.builder().message("Country deleted successfully.").build();
        return ResponseEntity.ok(response);
    }
}
