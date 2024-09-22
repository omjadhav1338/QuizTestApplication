package com.quiztest.QuizTestApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quizId;
    private String studentEmail;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String answerDetails;
    private Long questionCount;
    private Long score;
    private LocalDateTime testDate;

}
