package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.IndustryRepository;
import com.Ambalaj.Ambalaj.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl extends BaseServiceWithPaginationImpl<IndustryEntity, Long> implements IndustryService {
    private final IndustryRepository industryRepository;

    @Override
    protected JpaRepository<IndustryEntity, Long> getRepository() {
        return industryRepository;
    }

    @Override
    protected JpaSpecificationExecutor<IndustryEntity> getSpecificationExecutor() {
        return industryRepository;
    }

    @Override
    protected Specification<IndustryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            String searchPattern = "%" + search.toLowerCase() + "%";
            return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern));
        };
    }

    @Override
    public IndustryEntity addIndustry(IndustryEntity industryEntity) {
        return addEntity(industryEntity);
    }

    @Override
    public Page<IndustryEntity> getIndustries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                              String search) {
        return getPaginatedSortedSearchableEntities(page, pageSize, sortBy, sortDirection, search);
    }

    @Override
    public IndustryEntity getIndustry(Long industryId) {
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
