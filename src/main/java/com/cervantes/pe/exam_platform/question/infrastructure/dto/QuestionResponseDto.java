package com.cervantes.pe.exam_platform.question.infrastructure.dto;

import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamResponseDto;

public record QuestionResponseDto(
        Long id,
        String content,
        String image,
        String optionOne,
        String optionTwo,
        String optionThree,
        String optionFour,
        String correctAnswer,
        ExamResponseDto exam
) {
}
