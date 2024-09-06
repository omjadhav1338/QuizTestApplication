package com.quiztest.QuizTestApplication.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "answer_details")
public class AnswerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @ElementCollection
    @CollectionTable(name = "quiz_choices", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "choice")
    private List<String> choices;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "correct_answer")
    private String correctAnswer;
}
