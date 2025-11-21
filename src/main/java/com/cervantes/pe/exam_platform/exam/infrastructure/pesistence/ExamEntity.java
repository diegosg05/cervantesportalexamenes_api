package com.cervantes.pe.exam_platform.exam.infrastructure.pesistence;

import com.cervantes.pe.exam_platform.category.infrastructure.persistence.CategoryEntity;
import com.cervantes.pe.exam_platform.question.infrastructure.persistence.QuestionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {
    @Id
    @Column(name = "id_exam")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer maxPoints;
    @Column(nullable = false)
    private Integer quantityQuestions;
    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL)
    @Builder.Default
    private List<QuestionEntity> questions = new ArrayList<>();
}
