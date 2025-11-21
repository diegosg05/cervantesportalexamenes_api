package com.cervantes.pe.exam_platform.question.infrastructure.mapper;

import com.cervantes.pe.exam_platform.question.domain.entity.Question;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionRequestDto;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionResponseDto;
import com.cervantes.pe.exam_platform.question.infrastructure.persistence.QuestionEntity;

public interface QuestionMapper {
    QuestionEntity fromQuestionToEntity(Question question);
    Question fromEntityToQuestion(QuestionEntity questionEntity);
    QuestionResponseDto fromQuestionToDto(Question question);
    Question fromDtoToQuestion(QuestionRequestDto questionRequestDto);
}
