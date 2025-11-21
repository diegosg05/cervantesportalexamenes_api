package com.cervantes.pe.exam_platform.question.application.in;

import com.cervantes.pe.exam_platform.question.domain.entity.Question;

public interface CreateQuestionCase {
    Question save(Question question);
}
