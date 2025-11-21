package com.cervantes.pe.exam_platform.exam.infrastructure.controller;

import com.cervantes.pe.exam_platform.category.application.in.GetCategoryByIdCase;
import com.cervantes.pe.exam_platform.category.domain.exception.CategoryNotFoundException;
import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.exam.application.in.*;
import com.cervantes.pe.exam_platform.exam.domain.exception.ExamNotFoundException;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamRequestDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.dto.ExamResponseDto;
import com.cervantes.pe.exam_platform.exam.infrastructure.mapper.ExamMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    // use cases
    private final CreateExamCase createExamCase;
    private final DeleteExamCase deleteExamCase;
    private final GetAllExamsCase getAllExamsCase;
    private final GetAllExamsEnabledCase getAllExamsEnabledCase;
    private final GetAllExamsEnabledFromCategoryCase getAllExamsEnabledFromCategoryCase;
    private final GetAllExamsFromCategoryCase getAllExamsFromCategoryCase;
    private final GetExamByIdCase getExamByIdCase;
    private final UpdateExamCase updateExamCase;
    private final GetCategoryByIdCase getCategoryByIdCase;

    // mapper
    private final ExamMapper examMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<ExamResponseDto>> saveExam(@RequestBody @Valid ExamRequestDto examRequestDto) {
        var categoryOptional = getCategoryByIdCase.getById(examRequestDto.idCategory());

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(examRequestDto.idCategory());
        }

        var exam = examMapper.fromDtoToExam(examRequestDto);
        exam.setCategory(categoryOptional.get());

        var examSaved = createExamCase.save(exam);

        var examDtoSaved = examMapper.fromExamToDto(examSaved);
        var apiResponse = new ApiResponse<>(
                examDtoSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(examSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExamResponseDto>>> getAllExams() {
        var exams = getAllExamsCase.getAll();
        var examsDto = exams.stream()
                .map(examMapper::fromExamToDto)
                .toList();
        var apiResponse = new ApiResponse<>(
                examsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExamResponseDto>> getExamById(@PathVariable Long id) {
        var examOptional = getExamByIdCase.getById(id);

        if (examOptional.isEmpty()) {
            throw new ExamNotFoundException(id);
        }

        var examDto = examMapper.fromExamToDto(examOptional.get());
        var apiResponse = new ApiResponse<>(
                examDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<List<ExamResponseDto>>> getExamsFromCategory(@PathVariable Long id) {
        var categoryOptional = getCategoryByIdCase.getById(id);

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        var exams = getAllExamsFromCategoryCase.getAllFromCategory(categoryOptional.get());
        var examsDto = exams.stream()
                .map(examMapper::fromExamToDto)
                .toList();
        var apiResponse = new ApiResponse<>(
                examsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/enabled")
    public ResponseEntity<ApiResponse<List<ExamResponseDto>>> getExamsEnabled() {
        var exams = getAllExamsEnabledCase.getAllEnabled();
        var examsDto = exams.stream()
                .map(examMapper::fromExamToDto)
                .toList();

        var apiResponse = new ApiResponse<>(
                examsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/enabled/categories/{id}")
    public ResponseEntity<ApiResponse<List<ExamResponseDto>>> getExamsEnabledFromCategory(@PathVariable Long id) {
        var categoryOptional = getCategoryByIdCase.getById(id);

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        var exams = getAllExamsEnabledFromCategoryCase.getAllEnabledFromCategory(categoryOptional.get());
        var examsDto = exams.stream()
                .map(examMapper::fromExamToDto)
                .toList();

        var apiResponse = new ApiResponse<>(
                examsDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateExam(@RequestBody @Valid ExamRequestDto examRequestDto) {

        if (examIsEmpty(examRequestDto.id())) {
            throw new ExamNotFoundException(examRequestDto.id());
        }

        var categoryOptional = getCategoryByIdCase.getById(examRequestDto.idCategory());

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(examRequestDto.idCategory());
        }

        var exam = examMapper.fromDtoToExam(examRequestDto);
        exam.setCategory(categoryOptional.get());

        updateExamCase.update(exam);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        if (examIsEmpty(id)) {
            throw new ExamNotFoundException(id);
        }
        deleteExamCase.delete(id);

        return ResponseEntity.noContent().build();
    }

    private boolean examIsEmpty(Long id) {
        var examOptional = getExamByIdCase.getById(id);
        return examOptional.isEmpty();
    }
}
