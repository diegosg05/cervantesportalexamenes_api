package com.cervantes.pe.exam_platform.exam.infrastructure.repository;

import com.cervantes.pe.exam_platform.category.infrastructure.persistence.CategoryEntity;
import com.cervantes.pe.exam_platform.exam.infrastructure.pesistence.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaExamRepository extends JpaRepository<ExamEntity, Long> {
    List<ExamEntity> findByCategory(CategoryEntity category);
    List<ExamEntity> findByEnabled(Boolean enabled);
    List<ExamEntity> findByCategoryAndEnabled(CategoryEntity category, Boolean enabled);
}
