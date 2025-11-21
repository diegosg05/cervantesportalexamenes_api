package com.cervantes.pe.exam_platform.category.application.in;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;

import java.util.Optional;

public interface GetCategoryByIdCase {
    Optional<Category> getById(Long id);
}
