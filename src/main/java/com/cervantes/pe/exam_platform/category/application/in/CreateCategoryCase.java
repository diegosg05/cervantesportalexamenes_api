package com.cervantes.pe.exam_platform.category.application.in;

import com.cervantes.pe.exam_platform.category.domain.entity.Category;

public interface CreateCategoryCase {
    Category save(Category category);
}
