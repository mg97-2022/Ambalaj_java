package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.util.List;

public interface IndustryUseCase {
    IndustryDTO addIndustry(IndustryDTO industryDto);

    List<IndustryDTO> getAllIndustries();

    PaginatedDTO<IndustryDTO> getIndustries(
            Integer page, Integer pageSize, String sortBy, String sortDirection,
            String search);

    IndustryDTO getIndustry(Long industryId);

    IndustryDTO updateIndustry(IndustryDTO industryDto, Long industryId);

    void deleteIndustry(Long industryId);
}
