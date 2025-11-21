package com.cervantes.pe.exam_platform.question.infrastructure.dto;

public record QuestionResultDto(
        Double maxPoints,
        Integer correctAnswers,
        Integer attempts
) {
}
