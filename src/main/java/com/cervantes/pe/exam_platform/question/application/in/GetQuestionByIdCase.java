package com.cervantes.pe.exam_platform.question.application.in;

import com.cervantes.pe.exam_platform.question.domain.entity.Question;

import java.util.Optional;

public interface GetQuestionByIdCase {
    Optional<Question> getById(Long id);
}
