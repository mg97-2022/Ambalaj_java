package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.IndustryRepository;
import com.Ambalaj.Ambalaj.service.IndustryService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final JpaFeatures jpaFeatures;

    private Specification<IndustryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
    }

    public IndustryEntity addIndustry(IndustryEntity industryEntity) {
        return industryRepository.save(industryEntity);
    }

    @Override
    public List<IndustryEntity> getAllIndustries() {
        return industryRepository.findAll();
    }

    public Page<IndustryEntity> getIndustries(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        if (search != null && !search.trim().isEmpty()) {
            return industryRepository.findAll(getSearchSpecification(search), pageable);
        }
        return industryRepository.findAll(pageable);
    }

    public IndustryEntity getIndustry(Long industryId) {
        return industryRepository.findById(industryId).orElseThrow(() -> new NotFoundException("Industry", industryId));
    }

    public IndustryEntity updateIndustry(IndustryEntity updatedIndustry, Long industryId) {
        IndustryEntity existingIndustry = this.getIndustry(industryId);
        existingIndustry.setName(updatedIndustry.getName());
        return industryRepository.save(existingIndustry);
    }

    public void deleteIndustry(Long industryId) {
        if (!industryRepository.existsById(industryId)) throw new NotFoundException("Industry", industryId);
        industryRepository.deleteById(industryId);
    }
}
