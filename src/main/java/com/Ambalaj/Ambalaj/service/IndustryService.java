package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.IndustryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IndustryService {
    IndustryEntity addIndustry(IndustryEntity industryEntity);

    Page<IndustryEntity> getIndustries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                       String search);

    IndustryEntity getIndustry(Long industryId);

    IndustryEntity updateIndustry(IndustryEntity industryEntity, Long industryId);

    void deleteIndustry(Long industryId);
}
