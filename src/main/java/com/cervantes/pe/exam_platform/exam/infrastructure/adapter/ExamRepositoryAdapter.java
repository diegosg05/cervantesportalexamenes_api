package com.cervantes.pe.exam_platform.exam.infrastructure.adapter;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.category.infrastructure.mapper.CategoryMapper;
import com.cervantes.pe.exam_platform.exam.application.out.ExamRepositoryPort;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.exam.infrastructure.mapper.ExamMapper;
import com.cervantes.pe.exam_platform.exam.infrastructure.repository.JpaExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExamRepositoryAdapter implements ExamRepositoryPort {

    private final JpaExamRepository jpaExamRepository;
    private final ExamMapper examMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public Exam save(Exam exam) {
        var examEntity = examMapper.fromExamToEntity(exam);
        var examEntitySaved = jpaExamRepository.save(examEntity);
        return examMapper.fromEntityToExam(examEntitySaved);
    }

    @Override
    public List<Exam> getAll() {
        return jpaExamRepository.findAll()
                .stream()
                .map(examMapper::fromEntityToExam)
                .toList();
    }

    @Override
    public Optional<Exam> getById(Long id) {
        return jpaExamRepository.findById(id)
                .map(examMapper::fromEntityToExam);
    }

    @Override
    public void update(Exam exam) {
        var examEntity = examMapper.fromExamToEntity(exam);
        jpaExamRepository.save(examEntity);
    }

    @Override
    public void delete(Long id) {
        jpaExamRepository.deleteById(id);
    }

    @Override
    public List<Exam> getAllFromCategory(Category category) {
        return jpaExamRepository.findByCategory(categoryMapper.fromCategoryToEntity(category))
                .stream()
                .map(examMapper::fromEntityToExam)
                .toList();
    }

    @Override
    public List<Exam> getAllEnabled() {
        return jpaExamRepository.findByEnabled(true)
                .stream()
                .map(examMapper::fromEntityToExam)
                .toList();
    }

    @Override
    public List<Exam> getAllEnabledFromCategory(Category category) {
        return jpaExamRepository.findByCategoryAndEnabled(
                categoryMapper.fromCategoryToEntity(category),
                true
        ).stream()
                .map(examMapper::fromEntityToExam)
                .toList();
    }
}
