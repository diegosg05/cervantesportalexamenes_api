package com.cervantes.pe.exam_platform.question.application.out;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepositoryPort {
    Question save(Question question);
    void update(Question question);
    void delete(Long id);
    List<Question> getAll();
    Optional<Question> getById(Long id);
    List<Question> getFromExam(Exam exam);
}
