package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.SizeDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.SizeUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/size")
public class SizeController {
    private final SizeUseCase sizeUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<SizeDTO>> addSize(@Valid @RequestBody SizeDTO sizeDTO) {
        SizeDTO addedSize = sizeUseCase.addSize(sizeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.<SizeDTO>builder().data(addedSize).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<SizeDTO>>> getSizes(
    ) {
        List<SizeDTO> sizes = sizeUseCase.getAllSizes();
        return ResponseEntity.ok(ResponseDTO.<List<SizeDTO>>builder().data(sizes).build());
    }

    @GetMapping(path = "/{sizeId}")
    public ResponseEntity<ResponseDTO<SizeDTO>> getSize(@PathVariable Integer sizeId) {
        SizeDTO size = sizeUseCase.getSize(sizeId);
        return ResponseEntity.ok(ResponseDTO.<SizeDTO>builder().data(size).build());
    }

    @PutMapping(path = "/{sizeId}")
    public ResponseEntity<ResponseDTO<SizeDTO>> updateSize(
            @Valid @RequestBody SizeDTO sizeDto, @PathVariable Integer sizeId) {
        SizeDTO updatedSize = sizeUseCase.updateSize(sizeDto, sizeId);
        return ResponseEntity.ok(ResponseDTO.<SizeDTO>builder().data(updatedSize).build());
    }

    @DeleteMapping(path = "/{sizeId}")
    public ResponseEntity<Void> deleteSize(@PathVariable Integer sizeId) {
        sizeUseCase.deleteSize(sizeId);
        return ResponseEntity.noContent().build();
    }
}
