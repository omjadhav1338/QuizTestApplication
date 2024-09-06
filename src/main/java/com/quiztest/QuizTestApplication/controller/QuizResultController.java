package com.quiztest.QuizTestApplication.controller;

import com.quiztest.QuizTestApplication.dto.QuizResultDTO;
import com.quiztest.QuizTestApplication.entity.QuizResult;
import com.quiztest.QuizTestApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/student/result")
public class QuizResultController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/save-quiz-result")
    public ResponseEntity<QuizResult> saveResult(@RequestBody QuizResultDTO quizResultDTO) {

        QuizResult quizResult = new QuizResult();
        quizResult.setStudentEmail(quizResultDTO.getStudentEmail());
        quizResult.setSubject(quizResultDTO.getSubject());
        quizResult.setQuestionCount(quizResultDTO.getQuestionCount());
        quizResult.setAnswerDetails(quizResultDTO.getAnswerDetails());
        quizResult.setScore(quizResultDTO.getScore());
        quizResult.setTestDate(LocalDateTime.now());

        QuizResult savedResult = quizService.saveQuizResult(quizResult);

        return ResponseEntity.ok(savedResult);
    }

    @GetMapping("/getresult/{id}")
    public ResponseEntity<Optional<QuizResult>> fetchResult(@PathVariable Long id){
        Optional<QuizResult> quizResult = quizService.getResultsByID(id);
        return ResponseEntity.ok(quizResult);
    }
}