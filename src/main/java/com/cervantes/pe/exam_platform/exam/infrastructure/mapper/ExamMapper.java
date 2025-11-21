package com.cervantes.pe.exam_platform.exam.infrastructure.mapper;

import com.cervantes.pe.exam_platform.category.infrastructure.dto.CategoryDto;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamRequestDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamResponseDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.pesistence.ExamEntity;

public interface ExamMapper {
    ExamEntity fromExamToEntity(Exam exam);
    Exam fromEntityToExam(ExamEntity examEntity);
    ExamResponseDto fromExamToDto(Exam exam);
    Exam fromDtoToExam(ExamRequestDto examRequestDto);
}
