package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.SizeEntity;

import java.util.List;

public interface SizeService {
    SizeEntity addSize(SizeEntity sizeEntity);

    List<SizeEntity> getAllSizes();

    SizeEntity getSize(Integer sizeId);

    SizeEntity updateSize(SizeEntity sizeEntity, Integer sizeId);

    void deleteSize(Integer sizeId);
}
