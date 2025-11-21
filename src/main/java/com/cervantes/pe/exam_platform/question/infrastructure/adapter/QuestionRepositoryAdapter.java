package com.cervantes.pe.exam_platform.question.infrastructure.adapter;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.exam.infrastructure.mapper.ExamMapper;
import com.cervantes.pe.exam_platform.question.application.out.QuestionRepositoryPort;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;
import com.cervantes.pe.exam_platform.question.infrastructure.mapper.QuestionMapper;
import com.cervantes.pe.exam_platform.question.infrastructure.repository.JpaQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryAdapter implements QuestionRepositoryPort {

    private final JpaQuestionRepository jpaQuestionRepository;
    private final ExamMapper examMapper;
    private final QuestionMapper questionMapper;

    @Override
    public Question save(Question question) {
        var questionEntity = questionMapper.fromQuestionToEntity(question);
        var questionSaved = jpaQuestionRepository.save(questionEntity);
        return questionMapper.fromEntityToQuestion(questionSaved);
    }

    @Override
    public void update(Question question) {
        var questionEntity = questionMapper.fromQuestionToEntity(question);
        jpaQuestionRepository.save(questionEntity);
    }

    @Override
    public void delete(Long id) {
        jpaQuestionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAll() {
        return jpaQuestionRepository.findAll()
                .stream()
                .map(questionMapper::fromEntityToQuestion)
                .toList();
    }

    @Override
    public Optional<Question> getById(Long id) {
        return jpaQuestionRepository.findById(id)
                .map(questionMapper::fromEntityToQuestion);
    }

    @Override
    public List<Question> getFromExam(Exam exam) {
        return jpaQuestionRepository.findByExam(examMapper.fromExamToEntity(exam))
                .stream()
                .map(questionMapper::fromEntityToQuestion)
                .toList();
    }
}
