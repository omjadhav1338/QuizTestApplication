package com.quiztest.QuizTestApplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class QuizResultDTO {

    private String studentEmail;
    private String subject;
    private Long questionCount;
    private String answerDetails;  // Store answer details as JSON string
    private Long score;
}
