package com.cervantes.pe.exam_platform.category.infrastructure.adapter;

import com.cervantes.pe.exam_platform.category.application.out.CategoryRepositoryPort;
import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.category.infrastructure.mapper.CategoryMapper;
import com.cervantes.pe.exam_platform.category.infrastructure.repository.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        var categorySaved = jpaCategoryRepository.save(categoryMapper.fromCategoryToEntity(category));
        return categoryMapper.fromEntityToCategory(categorySaved);
    }

    @Override
    public List<Category> getAll() {
        return jpaCategoryRepository.findAll()
                .stream()
                .map(categoryMapper::fromEntityToCategory)
                .toList();
    }

    @Override
    public Optional<Category> getById(Long id) {
        var categoryOptional = jpaCategoryRepository.findById(id);
        return categoryOptional.map(categoryMapper::fromEntityToCategory);
    }

    @Override
    public void update(Category category) {
        jpaCategoryRepository.save(categoryMapper.fromCategoryToEntity(category));
    }

    @Override
    public void delete(Long id) {
        jpaCategoryRepository.deleteById(id);
    }
}
