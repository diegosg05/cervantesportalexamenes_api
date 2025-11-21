package com.cervantes.pe.exam_platform.category.infrastructure.controller;

import com.cervantes.pe.exam_platform.category.application.in.*;
import com.cervantes.pe.exam_platform.category.domain.exception.CategoryNotFoundException;
import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.category.infrastructure.dto.CategoryDto;
import com.cervantes.pe.exam_platform.category.infrastructure.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    // use cases
    private final CreateCategoryCase createCategoryCase;
    private final GetAllCategoriesCase getAllCategoriesCase;
    private final GetCategoryByIdCase getCategoryByIdCase;
    private final UpdateCategoryCase updateCategoryCase;
    private final DeleteCategoryCase deleteCategoryCase;
    // mapper
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> saveCategory(@RequestBody @Valid CategoryDto categoryDto) {
        var categorySaved = createCategoryCase.save(categoryMapper.fromDtoToCategory(categoryDto));
        var categoryDtoSaved = categoryMapper.fromCategoryToDto(categorySaved);
        var apiResponse = new ApiResponse<>(
                categoryDtoSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categorySaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {
        var categories = getAllCategoriesCase.getAll();
        var categoriesDto = categories
                .stream()
                .map(categoryMapper::fromCategoryToDto)
                .toList();
        var apiResponse = new ApiResponse<>(
                categoriesDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable Long id) {
        var categoryOptional = getCategoryByIdCase.getById(id);

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        var categoryDto = categoryMapper.fromCategoryToDto(categoryOptional.get());

        var apiResponse = new ApiResponse<>(
                categoryDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        if (categoryIsEmpty(categoryDto.id())) {
            throw new CategoryNotFoundException(categoryDto.id());
        }

        updateCategoryCase.update(categoryMapper.fromDtoToCategory(categoryDto));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryIsEmpty(id)) {
            throw new CategoryNotFoundException(id);
        }

        deleteCategoryCase.delete(id);

        return ResponseEntity.noContent().build();
    }

    private boolean categoryIsEmpty(Long id) {
        var categoryOptional = getCategoryByIdCase.getById(id);
        return categoryOptional.isEmpty();
    }
}
