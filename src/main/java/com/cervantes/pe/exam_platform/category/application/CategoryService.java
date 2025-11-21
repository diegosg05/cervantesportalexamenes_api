package com.cervantes.pe.exam_platform.category.application;

import com.cervantes.pe.exam_platform.category.application.in.*;
import com.cervantes.pe.exam_platform.category.application.out.CategoryRepositoryPort;
import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements CreateCategoryCase, GetAllCategoriesCase, GetCategoryByIdCase, UpdateCategoryCase, DeleteCategoryCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category save(Category category) {
        return categoryRepositoryPort.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepositoryPort.getAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepositoryPort.getById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepositoryPort.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepositoryPort.delete(id);
    }
}
