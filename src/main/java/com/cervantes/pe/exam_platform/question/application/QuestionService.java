package com.cervantes.pe.exam_platform.question.application;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.question.application.in.*;
import com.cervantes.pe.exam_platform.question.application.out.QuestionRepositoryPort;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService implements CreateQuestionCase, UpdateQuestionCase, DeleteQuestionCase,
        GetAllQuestionsCase, GetQuestionByIdCase, GetQuestionsFromExamCase {

    private final QuestionRepositoryPort questionRepositoryPort;

    @Override
    public Question save(Question question) {
        return questionRepositoryPort.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepositoryPort.delete(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepositoryPort.getAll();
    }

    @Override
    public Optional<Question> getById(Long id) {
        return questionRepositoryPort.getById(id);
    }

    @Override
    public List<Question> getFromExam(Exam exam) {
        return questionRepositoryPort.getFromExam(exam);
    }

    @Override
    public void update(Question question) {
        questionRepositoryPort.update(question);
    }
}
