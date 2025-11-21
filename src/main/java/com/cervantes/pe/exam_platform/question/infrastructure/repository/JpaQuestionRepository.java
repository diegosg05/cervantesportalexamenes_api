package com.cervantes.pe.exam_platform.question.infrastructure.repository;

import com.cervantes.pe.exam_platform.exam.infrastructure.pesistence.ExamEntity;
import com.cervantes.pe.exam_platform.question.infrastructure.persistence.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByExam(ExamEntity exam);
}
