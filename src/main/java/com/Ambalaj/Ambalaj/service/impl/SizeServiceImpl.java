package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.SizeEntity;
import com.Ambalaj.Ambalaj.repository.SizeRepository;
import com.Ambalaj.Ambalaj.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeEntity addSize(SizeEntity sizeEntity) {
        return sizeRepository.save(sizeEntity);
    }

    public List<SizeEntity> getAllSizes() {
        return sizeRepository.findAll();
    }

    public SizeEntity getSize(Integer sizeId) {
        return sizeRepository.findById(sizeId).orElseThrow(() -> new NotFoundException("Size", sizeId));
    }

    public SizeEntity updateSize(SizeEntity updatedSize, Integer sizeId) {
        SizeEntity existingSize = this.getSize(sizeId);
        existingSize.setName(updatedSize.getName());
        return sizeRepository.save(existingSize);
    }

    public void deleteSize(Integer sizeId) {
        if (!sizeRepository.existsById(sizeId)) throw new NotFoundException("Size", sizeId);
        sizeRepository.deleteById(sizeId);
    }
}
