package com.cervantes.pe.exam_platform.exam.application.out;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamRepositoryPort {
    Exam save(Exam exam);
    List<Exam> getAll();
    Optional<Exam> getById(Long id);
    void update(Exam exam);
    void delete(Long id);
    List<Exam> getAllFromCategory(Category category);
    List<Exam> getAllEnabled();
    List<Exam> getAllEnabledFromCategory(Category category);
}
