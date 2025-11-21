package com.cervantes.pe.exam_platform.category.infrastructure.mapper;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.category.infrastructure.dto.CategoryDto;
import com.cervantes.pe.exam_platform.category.infrastructure.persistence.CategoryEntity;

public interface CategoryMapper {
    CategoryEntity fromCategoryToEntity(Category category);
    Category fromEntityToCategory(CategoryEntity categoryEntity);
    CategoryDto fromCategoryToDto(Category category);
    Category fromDtoToCategory(CategoryDto categoryDto);
}
