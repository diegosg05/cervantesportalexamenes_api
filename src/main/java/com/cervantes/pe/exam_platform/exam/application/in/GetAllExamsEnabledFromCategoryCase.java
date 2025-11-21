package com.cervantes.pe.exam_platform.exam.application.in;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;
import com.cervantes.pe.exam_platform.exam.domain.entity.Exam;

import java.util.List;

public interface GetAllExamsEnabledFromCategoryCase {
    List<Exam> getAllEnabledFromCategory(Category category);
}
