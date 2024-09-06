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
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String subject;
    @ElementCollection
    private List<String> choices;
    @ElementCollection
    private List<String> correctChoice;
}
