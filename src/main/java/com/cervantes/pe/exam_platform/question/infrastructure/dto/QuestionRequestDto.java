package com.cervantes.pe.exam_platform.question.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record QuestionRequestDto(
        Long id,
        @NotBlank String content,
        String image,
        @NotBlank String optionOne,
        @NotBlank String optionTwo,
        String optionThree,
        String optionFour,
        @NotBlank String correctAnswer,
        @Min(1) Long idExam
) {
}
