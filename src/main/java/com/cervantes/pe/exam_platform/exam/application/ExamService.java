package com.cervantes.pe.exam_platform.exam.application;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.exam.application.in.*;
import com.cervantes.pe.exam_platform.exam.application.out.ExamRepositoryPort;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService implements CreateExamCase, DeleteExamCase, GetAllExamsCase,
        GetAllExamsFromCategoryCase, GetAllExamsEnabledCase, GetAllExamsEnabledFromCategoryCase,
        GetExamByIdCase, UpdateExamCase{

    private final ExamRepositoryPort examRepositoryPort;

    @Override
    public Exam save(Exam exam) {
        return examRepositoryPort.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepositoryPort.delete(id);
    }

    @Override
    public List<Exam> getAll() {
        return examRepositoryPort.getAll();
    }

    @Override
    public List<Exam> getAllEnabled() {
        return examRepositoryPort.getAllEnabled();
    }

    @Override
    public List<Exam> getAllEnabledFromCategory(Category category) {
        return examRepositoryPort.getAllEnabledFromCategory(category);
    }

    @Override
    public List<Exam> getAllFromCategory(Category category) {
        return examRepositoryPort.getAllFromCategory(category);
    }

    @Override
    public Optional<Exam> getById(Long id) {
        return examRepositoryPort.getById(id);
    }

    @Override
    public void update(Exam exam) {
        examRepositoryPort.update(exam);
    }
}
