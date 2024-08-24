package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.IndustryRepository;
import com.Ambalaj.Ambalaj.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl extends BaseServiceImpl<IndustryEntity, Long> implements IndustryService {
    private final IndustryRepository industryRepository;

    @Override
    protected JpaRepository<IndustryEntity, Long> getRepository() {
        return industryRepository;
    }

    @Override
    public IndustryEntity addIndustry(IndustryEntity industryEntity) {
        return addEntity(industryEntity);
    }

    @Override
    public List<IndustryEntity> getIndustries() {
        return getEntities();
    }

    @Override
    public IndustryEntity getIndustryById(Long industryId) {
        return getEntityById(industryId, "Industry");
    }

    @Override
    protected void updateEntityFields(IndustryEntity existingIndustry, IndustryEntity updatedIndustry) {
        existingIndustry.setName(updatedIndustry.getName());
    }

    @Override
    public IndustryEntity updateIndustry(IndustryEntity industryEntity, Long industryId) {
        return updateEntity(industryEntity, industryId, "Industry");
    }

    @Override
    public void deleteIndustry(Long industryId) {
        deleteEntity(industryId, "Industry");
    }
}
