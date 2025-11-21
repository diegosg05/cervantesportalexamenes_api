package com.cervantes.pe.exam_platform.question.application.in;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;
import com.cervantes.pe.exam_platform.question.domain.entity.Question;

import java.util.List;

public interface GetQuestionsFromExamCase {
    List<Question> getFromExam(Exam exam);
}
