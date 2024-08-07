package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.IndustryRepository;
import com.Ambalaj.Ambalaj.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;

    @Override
    public List<IndustryEntity> getIndustriesByIds(List<Long> industryIds) {
        List<IndustryEntity> industries = industryRepository.findAllById(industryIds);
        Set<Long> foundIds = industries.stream().map(IndustryEntity::getId).collect(Collectors.toSet());
        List<Long> missingIds = industryIds.stream().filter(id -> !foundIds.contains(id)).toList();
        if (!missingIds.isEmpty()) {
            throw new NotFoundException("Industries not found for IDs: " + missingIds);
        }
        return industries;
    }
}
