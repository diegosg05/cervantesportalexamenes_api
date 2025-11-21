package com.cervantes.pe.exam_platform.exam.application.in;

import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;

public interface CreateExamCase {
    Exam save(Exam exam);
    
}
