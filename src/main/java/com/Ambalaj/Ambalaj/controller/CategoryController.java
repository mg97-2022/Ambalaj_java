package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.CategoryUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<CategoryDTO>> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO addedCategory = categoryUseCase.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<CategoryDTO>builder().data(addedCategory).build());
    }

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDTO<List<CategoryDTO>>> getAllCategories(
            @RequestParam(defaultValue = "false", required = false) boolean parentOnly) {
        List<CategoryDTO> allCategories = categoryUseCase.getAllCategories(parentOnly);
        return ResponseEntity.ok(ResponseDTO.<List<CategoryDTO>>builder().data(allCategories).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<CategoryDTO>>> getCategories(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<CategoryDTO> categories =
                categoryUseCase.getCategories(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<CategoryDTO>>builder().data(categories).build());
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<ResponseDTO<CategoryDTO>> getCategory(@PathVariable Long categoryId) {
        CategoryDTO category = categoryUseCase.getCategory(categoryId);
        return ResponseEntity.ok(ResponseDTO.<CategoryDTO>builder().data(category).build());
    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<ResponseDTO<CategoryDTO>> updateCategory(
            @Valid @RequestBody CategoryDTO categoryDto, @PathVariable Long categoryId) {
        CategoryDTO updatedCategory = categoryUseCase.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(ResponseDTO.<CategoryDTO>builder().data(updatedCategory).build());
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryUseCase.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
