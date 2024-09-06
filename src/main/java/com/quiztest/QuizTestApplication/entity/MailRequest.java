package com.quiztest.QuizTestApplication.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    private String mail;
    private String subject;
    private String body;
}
