package com.cervantes.pe.exam_platform.exam.domain.entity;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam {
    private Long id;
    private String title;
    private String description;
    private Integer maxPoints;
    private Integer quantityQuestions;
    private Boolean enabled;
    private Category category;
    private List<Question> questions;
}
