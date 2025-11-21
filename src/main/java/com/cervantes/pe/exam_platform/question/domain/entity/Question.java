package com.cervantes.pe.exam_platform.question.domain.entity;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String content;
    private String image;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String correctAnswer;
    private Exam exam;
}
