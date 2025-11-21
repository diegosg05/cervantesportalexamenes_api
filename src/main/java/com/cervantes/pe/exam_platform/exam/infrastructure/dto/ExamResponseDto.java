package com.cervantes.pe.exam_platform.exam.infrastructure.dto;

import com.cervantes.pe.exam_platform.category.infrastructure.dto.CategoryDto;

public record ExamResponseDto(
        Long id,
        String title,
        String description,
        Integer maxPoints,
        Integer quantityQuestions,
        Boolean enabled,
        CategoryDto category
) {
}
