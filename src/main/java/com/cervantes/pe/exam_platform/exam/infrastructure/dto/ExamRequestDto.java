package com.cervantes.pe.exam_platform.exam.infrastructure.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExamRequestDto(
        Long id,
        @NotBlank String title,
        @NotBlank String description,
        @Min(1) @Max(20) Integer maxPoints,
        @Min(1) Integer quantityQuestions,
        @NotNull Boolean enabled,
        @Min(1) Long idCategory
) {
}
