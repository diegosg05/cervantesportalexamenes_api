package com.cervantes.pe.exam_platform.question.infrastructure.controller;

import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.exam.application.in.GetExamByIdCase;
import com.cervantes.pe.exam_platform.exam.domain.exception.ExamNotFoundException;
import com.cervantes.pe.exam_platform.question.application.in.*;
import com.cervantes.pe.exam_platform.question.domain.exception.QuestionNotFoundException;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionEvaluatedDto;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionRequestDto;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionResponseDto;
import com.cervantes.pe.exam_platform.question.infrastructure.dto.QuestionResultDto;
import com.cervantes.pe.exam_platform.question.infrastructure.mapper.QuestionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final CreateQuestionCase createQuestionCase;
    private final GetAllQuestionsCase getAllQuestionsCase;
    private final GetQuestionByIdCase getQuestionByIdCase;
    private final GetExamByIdCase getExamByIdCase;
    private final UpdateQuestionCase updateQuestionCase;
    private final DeleteQuestionCase deleteQuestionCase;
    private final GetQuestionsFromExamCase getQuestionsFromExamCase;

    private final QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<QuestionResponseDto>> saveQuestion(@RequestBody @Valid QuestionRequestDto questionRequestDto) {
        var examOptional = getExamByIdCase.getById(questionRequestDto.idExam());

        if (examOptional.isEmpty()) {
            throw new ExamNotFoundException(questionRequestDto.idExam());
        }

        var question = questionMapper.fromDtoToQuestion(questionRequestDto);
        question.setExam(examOptional.get());

        var questionSaved = createQuestionCase.save(question);
        var questionDtoSaved = questionMapper.fromQuestionToDto(questionSaved);
        var apiResponse = new ApiResponse<>(
                questionDtoSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(questionSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuestionResponseDto>>> getAllQuestions() {
        var questions = getAllQuestionsCase.getAll();
        var questionsDto = questions.stream()
                .map(questionMapper::fromQuestionToDto)
                .toList();
        var apiResponse = new ApiResponse<>(
                questionsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponseDto>> getQuestionById(@PathVariable Long id) {
        var questionOptional = getQuestionByIdCase.getById(id);

        if (questionOptional.isEmpty()) {
            throw new QuestionNotFoundException(id);
        }

        var questionDto = questionMapper.fromQuestionToDto(questionOptional.get());
        var apiResponse = new ApiResponse<>(
                questionDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateQuestion(@RequestBody @Valid QuestionRequestDto questionRequestDto) {
        if (questionIsEmpty(questionRequestDto.id())) {
            throw new QuestionNotFoundException(questionRequestDto.id());
        }

        var examOptional = getExamByIdCase.getById(questionRequestDto.idExam());

        if (examOptional.isEmpty()) {
            throw new ExamNotFoundException(questionRequestDto.idExam());
        }

        var question = questionMapper.fromDtoToQuestion(questionRequestDto);
        question.setExam(examOptional.get());

        updateQuestionCase.update(question);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (questionIsEmpty(id)) {
            throw new QuestionNotFoundException(id);
        }

        deleteQuestionCase.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<ApiResponse<List<QuestionResponseDto>>> getQuestionsFromExam(@PathVariable Long id) {
        var examOptional = getExamByIdCase.getById(id);

        if (examOptional.isEmpty()) {
            throw new ExamNotFoundException(id);
        }

        var questions = getQuestionsFromExamCase.getFromExam(examOptional.get());
        var questionsDto = questions.stream()
                .map(questionMapper::fromQuestionToDto)
                .toList();
        var apiResponse = new ApiResponse<>(
                questionsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/evaluate-exam")
    public ResponseEntity<ApiResponse<QuestionResultDto>> evaluateExam(@RequestBody List<QuestionEvaluatedDto> questionsAnswered) {
        double maxPoints = 0;
        int correctAnswers = 0;
        int attempts = 0;

        for (var questionAnswered : questionsAnswered) {
            var questionOptional = getQuestionByIdCase.getById(questionAnswered.id());

            if (questionOptional.isEmpty()) {
                throw new QuestionNotFoundException(questionAnswered.id());
            }

            var question = questionOptional.get();
            if (question.getCorrectAnswer().equals(questionAnswered.givenCorrect())) {
                correctAnswers++;
                double points = (double) question.getExam().getMaxPoints()/questionsAnswered.size();
                maxPoints += points;
            }
            if (questionAnswered.givenCorrect() != null) {
                attempts++;
            }
        }

        var questionResults = new QuestionResultDto(
                maxPoints,
                correctAnswers,
                attempts
        );

        var apiResponse = new ApiResponse<>(
                questionResults,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    private boolean questionIsEmpty(Long id) {
        var questionOptional = getQuestionByIdCase.getById(id);
        return questionOptional.isEmpty();
    }

}
