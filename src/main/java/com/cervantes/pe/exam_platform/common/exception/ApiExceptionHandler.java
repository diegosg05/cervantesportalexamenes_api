package com.cervantes.pe.exam_platform.common.exception;

import com.cervantes.pe.exam_platform.category.domain.exception.CategoryNotFoundException;
import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.exam.domain.exception.ExamNotFoundException;
import com.cervantes.pe.exam_platform.question.domain.exception.QuestionNotFoundException;
import com.cervantes.pe.exam_platform.user.domain.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> badRequest(HttpServletRequest request,
                                                  MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
                );

        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI(),
                        errors
                )
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(HttpServletRequest request,
                                                        CategoryNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI()
                )
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ExamNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(HttpServletRequest request,
                                                        ExamNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI()
                )
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(HttpServletRequest request,
                                                        QuestionNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI()
                )
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(HttpServletRequest request,
                                                        UserNotFoundException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI()
                )
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> notFound(HttpServletRequest request,
                                                        BadCredentialsException ex) {
        var apiResponse = new ApiResponse<>(
                null,
                new ErrorMessage(
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        request.getRequestURI()
                )
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

}
