package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.IndustryEntity;

import java.util.List;

public interface IndustryService {
    List<IndustryEntity> getIndustriesByIds(List<Long> industryIds);
}
