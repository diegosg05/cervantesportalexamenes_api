package com.cervantes.pe.exam_platform.question.infrastructure.persistence;

import com.cervantes.pe.exam_platform.exam.infrastructure.pesistence.ExamEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long id;
    @Column(nullable = false)
    private String content;
    private String image;
    @Column(nullable = false)
    private String optionOne;
    @Column(nullable = false)
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    @Column(nullable = false)
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "id_exam", nullable = false)
    private ExamEntity exam;
}
