package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.ColorEntity;

import java.util.List;

public interface ColorService {
    ColorEntity addColor(ColorEntity colorEntity);

    List<ColorEntity> getAllColors();

    ColorEntity getColor(Integer colorId);

    ColorEntity updateColor(ColorEntity colorEntity, Integer colorId);

    void deleteColor(Integer colorId);
}
