package com.cervantes.pe.exam_platform.question.infrastructure.mapper.impl;

import com.cervantes.pe.exam_platform.exam.infrastructure.mapper.ExamMapper;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionRequestDto;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionResponseDto;
import com.cervantes.pe.exam_platform.question.infrastructure.mapper.QuestionMapper;
import com.cervantes.pe.exam_platform.question.infrastructure.persistence.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final ExamMapper examMapper;

    @Override
    public QuestionEntity fromQuestionToEntity(Question question) {
        return QuestionEntity.builder()
                .id(question.getId())
                .content(question.getContent())
                .image(question.getImage())
                .optionOne(question.getOptionOne())
                .optionTwo(question.getOptionTwo())
                .optionThree(question.getOptionThree())
                .optionFour(question.getOptionFour())
                .correctAnswer(question.getCorrectAnswer())
                .exam(examMapper.fromExamToEntity(question.getExam()))
                .build();
    }

    @Override
    public Question fromEntityToQuestion(QuestionEntity questionEntity) {
        return Question.builder()
                .id(questionEntity.getId())
                .content(questionEntity.getContent())
                .image(questionEntity.getImage())
                .optionOne(questionEntity.getOptionOne())
                .optionTwo(questionEntity.getOptionTwo())
                .optionThree(questionEntity.getOptionThree())
                .optionFour(questionEntity.getOptionFour())
                .correctAnswer(questionEntity.getCorrectAnswer())
                .exam(examMapper.fromEntityToExam(questionEntity.getExam()))
                .build();
    }

    @Override
    public QuestionResponseDto fromQuestionToDto(Question question) {
        return new QuestionResponseDto(
                question.getId(),
                question.getContent(),
                question.getImage(),
                question.getOptionOne(),
                question.getOptionTwo(),
                question.getOptionThree(),
                question.getOptionFour(),
                question.getCorrectAnswer(),
                examMapper.fromExamToDto(question.getExam())
        );
    }

    @Override
    public Question fromDtoToQuestion(QuestionRequestDto questionRequestDto) {
        return Question.builder()
                .id(questionRequestDto.id())
                .content(questionRequestDto.content())
                .image(questionRequestDto.image())
                .optionOne(questionRequestDto.optionOne())
                .optionTwo(questionRequestDto.optionTwo())
                .optionThree(questionRequestDto.optionThree())
                .optionFour(questionRequestDto.optionFour())
                .correctAnswer(questionRequestDto.correctAnswer())
                .build();
    }
}
