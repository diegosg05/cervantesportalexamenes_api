package com.cervantes.pe.exam_platform.question.domain.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Long id) {
        super("Question with id " + id + " not founded");
    }
}
