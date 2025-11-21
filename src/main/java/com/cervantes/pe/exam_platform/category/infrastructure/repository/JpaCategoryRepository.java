package com.cervantes.pe.exam_platform.category.infrastructure.repository;

import com.cervantes.pe.exam_platform.category.infrastructure.persistence.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
