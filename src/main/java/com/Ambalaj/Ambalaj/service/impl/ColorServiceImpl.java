package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.ColorEntity;
import com.Ambalaj.Ambalaj.repository.ColorRepository;
import com.Ambalaj.Ambalaj.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl extends BaseServiceImpl<ColorEntity, Integer> implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    protected JpaRepository<ColorEntity, Integer> getRepository() {
        return colorRepository;
    }

    @Override
    public ColorEntity addColor(ColorEntity colorEntity) {
        return addEntity(colorEntity);
    }

    @Override
    public List<ColorEntity> getAllColors() {
        return getEntities();
    }

    @Override
    public ColorEntity getColor(Integer colorId) {
        return getEntityById(colorId, "Color");
    }

    @Override
    protected void updateEntityFields(ColorEntity existingColor, ColorEntity updatedColor) {
        existingColor.setName(updatedColor.getName());
        existingColor.setHexCode(updatedColor.getHexCode());
    }

    @Override
    public ColorEntity updateColor(ColorEntity colorEntity, Integer colorId) {
        return updateEntity(colorEntity, colorId, "Color");
    }

    @Override
    public void deleteColor(Integer colorId) {
        deleteEntity(colorId, "Color");
    }
}
