package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.IndustryEntity;

import java.util.List;

public interface IndustryService {
    IndustryEntity addIndustry(IndustryEntity industryEntity);

    List<IndustryEntity> getIndustries();

    IndustryEntity getIndustryById(Long industryId);

    IndustryEntity updateIndustry(IndustryEntity industryEntity, Long industryId);

    void deleteIndustry(Long industryId);
}
