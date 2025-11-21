package com.cervantes.pe.exam_platform.exam.infrastructure.mapper.impl;

import com.cervantes.pe.exam_platform.category.infrastructure.mapper.CategoryMapper;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamRequestDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamResponseDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.mapper.ExamMapper;
import com.cervantes.pe.exam_platform.exam.infrastructure.pesistence.ExamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamMapperImpl implements ExamMapper {

    private final CategoryMapper categoryMapper;

    @Override
    public ExamEntity fromExamToEntity(Exam exam) {
        return ExamEntity.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .quantityQuestions(exam.getQuantityQuestions())
                .enabled(exam.getEnabled())
                .description(exam.getDescription())
                .maxPoints(exam.getMaxPoints())
                .category(categoryMapper.fromCategoryToEntity(exam.getCategory()))
                .build();
    }

    @Override
    public Exam fromEntityToExam(ExamEntity examEntity) {
        return Exam.builder()
                .quantityQuestions(examEntity.getQuantityQuestions())
                .maxPoints(examEntity.getMaxPoints())
                .title(examEntity.getTitle())
                .id(examEntity.getId())
                .description(examEntity.getDescription())
                .enabled(examEntity.getEnabled())
                .category(categoryMapper.fromEntityToCategory(examEntity.getCategory()))
                .build();
    }

    @Override
    public ExamResponseDto fromExamToDto(Exam exam) {
        return new ExamResponseDto(
                exam.getId(),
                exam.getTitle(),
                exam.getDescription(),
                exam.getMaxPoints(),
                exam.getQuantityQuestions(),
                exam.getEnabled(),
                categoryMapper.fromCategoryToDto(exam.getCategory())
        );
    }

    @Override
    public Exam fromDtoToExam(ExamRequestDto examRequestDto) {
        return Exam.builder()
                .id(examRequestDto.id())
                .title(examRequestDto.title())
                .description(examRequestDto.description())
                .maxPoints(examRequestDto.maxPoints())
                .quantityQuestions(examRequestDto.quantityQuestions())
                .enabled(examRequestDto.enabled())
                .build();
    }
}
