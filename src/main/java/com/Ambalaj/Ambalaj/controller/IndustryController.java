package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.IndustryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/industry")
@RequiredArgsConstructor
public class IndustryController {
    private final IndustryUseCase industryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO> addIndustry(@Valid @RequestBody IndustryDTO industryDTO) {
        IndustryDTO addedIndustry = industryUseCase.addIndustry(industryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.builder().data(addedIndustry).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getIndustries() {
        List<IndustryDTO> industries = industryUseCase.getIndustries();
        return ResponseEntity.ok(ResponseDTO.builder().data(industries).build());
    }

    @GetMapping(path = "/{industryId}")
    public ResponseEntity<ResponseDTO> getIndustryDetails(@PathVariable Long industryId) {
        IndustryDTO industry = industryUseCase.getIndustryById(industryId);
        return ResponseEntity.ok(ResponseDTO.builder().data(industry).build());
    }

    @PutMapping(path = "/{industryId}")
    public ResponseEntity<ResponseDTO> updateIndustry(@Valid @RequestBody IndustryDTO industryDto,
                                                      @PathVariable Long industryId) {
        IndustryDTO updatedIndustry = industryUseCase.updateIndustry(industryDto, industryId);
        return ResponseEntity.ok(ResponseDTO.builder().data(updatedIndustry).build());
    }

    @DeleteMapping(path = "/{industryId}")
    public ResponseEntity<ResponseDTO> deleteIndustry(@PathVariable Long industryId) {
        industryUseCase.deleteIndustry(industryId);
        return ResponseEntity.ok(ResponseDTO.builder().message("Industry deleted successfully.").build());
    }
}
