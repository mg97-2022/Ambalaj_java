package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.SizeEntity;
import com.Ambalaj.Ambalaj.repository.SizeRepository;
import com.Ambalaj.Ambalaj.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl extends BaseServiceImpl<SizeEntity, Integer> implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    protected JpaRepository<SizeEntity, Integer> getRepository() {
        return sizeRepository;
    }

    @Override
    public SizeEntity addSize(SizeEntity sizeEntity) {
        return addEntity(sizeEntity);
    }

    @Override
    public List<SizeEntity> getAllSizes() {
        return getEntities();
    }

    @Override
    public SizeEntity getSize(Integer sizeId) {
        return getEntityById(sizeId, "Size");
    }

    @Override
    protected void updateEntityFields(SizeEntity existingSize, SizeEntity updatedSize) {
        existingSize.setName(updatedSize.getName());
    }

    @Override
    public SizeEntity updateSize(SizeEntity sizeEntity, Integer sizeId) {
        return updateEntity(sizeEntity, sizeId, "Size");
    }

    @Override
    public void deleteSize(Integer sizeId) {
        deleteEntity(sizeId, "Size");
    }
}
