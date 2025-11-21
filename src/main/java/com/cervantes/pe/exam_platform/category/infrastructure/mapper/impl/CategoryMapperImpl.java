package com.cervantes.pe.exam_platform.category.infrastructure.mapper.impl;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.category.infrastructure.dto.CategoryDto;
import com.cervantes.pe.exam_platform.category.infrastructure.mapper.CategoryMapper;
import com.cervantes.pe.exam_platform.category.infrastructure.persistence.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryEntity fromCategoryToEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    @Override
    public Category fromEntityToCategory(CategoryEntity categoryEntity) {
            return Category.builder()
                    .id(categoryEntity.getId())
                    .title(categoryEntity.getTitle())
                    .description(categoryEntity.getDescription())
                    .build();
    }

    @Override
    public CategoryDto fromCategoryToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getTitle(),
                category.getDescription()
        );
    }

    @Override
    public Category fromDtoToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.id())
                .title(categoryDto.title())
                .description(categoryDto.description())
                .build();
    }
}
