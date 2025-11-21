package com.cervantes.pe.exam_platform.exam.application.in;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;

import java.util.Optional;

public interface GetExamByIdCase {
    Optional<Exam> getById(Long id);
}
