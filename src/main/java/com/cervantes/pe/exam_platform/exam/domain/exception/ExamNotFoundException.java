package com.cervantes.pe.exam_platform.exam.domain.exception;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException(Long id) {
        super("Exam with id " + id + "not founded");
    }
}
