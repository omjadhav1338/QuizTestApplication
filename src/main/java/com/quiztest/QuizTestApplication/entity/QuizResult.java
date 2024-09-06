package com.quiztest.QuizTestApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentEmail;
    private String subject;
    private Long questionCount;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String answerDetails;
    private Long score;
    private LocalDateTime testDate;
}
