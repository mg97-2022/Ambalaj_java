package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.IndustryMapper;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.service.IndustryService;
import com.Ambalaj.Ambalaj.useCase.IndustryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IndustryUseCaseImpl implements IndustryUseCase {
    private final IndustryService industryService;
    private final IndustryMapper industryMapper;

    @Override
    public IndustryDTO addIndustry(IndustryDTO industryDto) {
        IndustryEntity industryEntity = industryMapper.toEntity(industryDto);
        IndustryEntity addedIndustry = industryService.addIndustry(industryEntity);
        return industryMapper.toDto(addedIndustry);
    }

    @Override
    public List<IndustryDTO> getAllIndustries() {
        List<IndustryEntity> industries = industryService.getAllIndustries();
        return industryMapper.toListDto(industries);
    }

    @Override
    public PaginatedDTO<IndustryDTO> getIndustries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                                   String search) {
        Page<IndustryEntity> industryList =
                industryService.getIndustries(page, pageSize, sortBy, sortDirection, search);
        return industryMapper.toPaginatedDto(industryList);
    }

    @Override
    public IndustryDTO getIndustry(Long industryId) {
        IndustryEntity industry = industryService.getIndustry(industryId);
        return industryMapper.toDto(industry);
    }

    @Override
    public IndustryDTO updateIndustry(IndustryDTO industryDto, Long industryId) {
        IndustryEntity industryEntity = industryMapper.toEntity(industryDto);
        IndustryEntity updatedIndustry = industryService.updateIndustry(industryEntity, industryId);
        return industryMapper.toDto(updatedIndustry);
    }

    @Override
    public void deleteIndustry(Long industryId) {
        industryService.deleteIndustry(industryId);
    }
}
