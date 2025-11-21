package com.cervantes.pe.exam_platform.category.application.out;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {
    Category save(Category category);
    List<Category> getAll();
    Optional<Category> getById(Long id);
    void update(Category category);
    void delete(Long id);
}
