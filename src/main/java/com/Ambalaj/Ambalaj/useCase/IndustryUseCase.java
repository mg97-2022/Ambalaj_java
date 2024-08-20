package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;

import java.util.List;

public interface IndustryUseCase {
    IndustryDTO addIndustry(IndustryDTO industryDto);

    List<IndustryDTO> getIndustries();

    IndustryDTO getIndustryById(Long industryId);

    IndustryDTO updateIndustry(IndustryDTO industryDto, Long industryId);

    void deleteIndustry(Long industryId);
}
