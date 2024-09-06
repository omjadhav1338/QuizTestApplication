package com.quiztest.QuizTestApplication.controller;


import com.quiztest.QuizTestApplication.entity.Quiz;
import com.quiztest.QuizTestApplication.entity.QuizResult;
import com.quiztest.QuizTestApplication.repository.QuizResultRepository;
import com.quiztest.QuizTestApplication.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/quizzes")
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @PostMapping("/add-new-quiz")
    public ResponseEntity <Quiz> createQuiz(@RequestBody Quiz quiz){
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.status(CREATED).body(createdQuiz);
    }

    @GetMapping("/all-quizzes")
    public ResponseEntity <List<Quiz>> getAllQuestions(){
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @DeleteMapping("/delete-quiz/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-quiz/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        Quiz quiz = quizService.updateQuiz(id, updatedQuiz);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuestionByID(@PathVariable Long id){
        Quiz quiz=quizService.getQuizByID(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody QuizResult quizResult) {
        quizResult.setTestDate(LocalDateTime.now());
        quizService.saveQuizResult(quizResult);
        return ResponseEntity.ok("Quiz submitted successfully!");
    }

    @GetMapping("/results/{studentEmail}")
    public List<QuizResult> getResults(@PathVariable String studentEmail) {
        return quizResultRepository.findByStudentEmail(studentEmail);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Quiz>> getRandomQuizzes(
            @RequestParam String subject,
            @RequestParam int limit) {

        List<Quiz> quizzes = quizService.getRandomQuizzes(subject, limit);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getQuizzes(
            @RequestParam String subject,
            @RequestParam int limit,
            @RequestParam(required = false, defaultValue = "false") boolean random) {

        List<Quiz> quizzes;
        if (random) {
            quizzes = quizService.getRandomQuizzes(subject, limit);
        } else {
            quizzes = quizService.getQuizzesBySubject(subject, limit);
        }
        return ResponseEntity.ok(quizzes);
    }

}
