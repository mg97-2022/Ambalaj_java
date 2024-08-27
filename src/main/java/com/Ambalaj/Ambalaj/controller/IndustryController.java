package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.IndustryUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/industry")
@RequiredArgsConstructor
public class IndustryController {
    private final IndustryUseCase industryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<IndustryDTO>> addIndustry(@Valid @RequestBody IndustryDTO industryDTO) {
        IndustryDTO addedIndustry = industryUseCase.addIndustry(industryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<IndustryDTO>builder().data(addedIndustry).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<IndustryDTO>>> getIndustries(
            @RequestParam(defaultValue = "0", required = false) @Min(0) @NotNull Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) @NotNull Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<IndustryDTO> industries =
                industryUseCase.getIndustries(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<IndustryDTO>>builder().data(industries).build());
    }

    @GetMapping(path = "/{industryId}")
    public ResponseEntity<ResponseDTO<IndustryDTO>> getIndustry(@PathVariable Long industryId) {
        IndustryDTO industry = industryUseCase.getIndustry(industryId);
        return ResponseEntity.ok(ResponseDTO.<IndustryDTO>builder().data(industry).build());
    }

    @PutMapping(path = "/{industryId}")
    public ResponseEntity<ResponseDTO<IndustryDTO>> updateIndustry(
            @Valid @RequestBody IndustryDTO industryDto, @PathVariable Long industryId) {
        IndustryDTO updatedIndustry = industryUseCase.updateIndustry(industryDto, industryId);
        return ResponseEntity.ok(ResponseDTO.<IndustryDTO>builder().data(updatedIndustry).build());
    }

    @DeleteMapping(path = "/{industryId}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable Long industryId) {
        industryUseCase.deleteIndustry(industryId);
        return ResponseEntity.noContent().build();
    }
}
