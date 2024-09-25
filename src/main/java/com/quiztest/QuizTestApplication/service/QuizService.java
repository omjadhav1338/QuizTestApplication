package com.quiztest.QuizTestApplication.service;

import com.quiztest.QuizTestApplication.entity.Quiz;
import com.quiztest.QuizTestApplication.entity.QuizResult;
import com.quiztest.QuizTestApplication.repository.QuizRepository;
import com.quiztest.QuizTestApplication.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        existingQuiz.setQuestion(updatedQuiz.getQuestion());
        existingQuiz.setSubject(updatedQuiz.getSubject());
        existingQuiz.setChoices(updatedQuiz.getChoices());
        existingQuiz.setCorrectChoice(updatedQuiz.getCorrectChoice());
        return quizRepository.save(existingQuiz);
    }

    public Quiz getQuizByID(Long id){
        Quiz quiz = quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        return quiz;
    }


    public List<Quiz> getQuizzesBySubjectAndLimit(String subject, int limit) {

        return quizRepository.findBySubjectWithLimit(subject, limit);
    }

    public List<Quiz> getRandomQuizzes(String subject, int limit) {
        return quizRepository.findRandomQuizzesBySubject(subject, limit);
    }

    public List<Quiz> getQuizzesBySubject(String subject, int limit) {
        return quizRepository.findQuizzesBySubject(subject, limit);
    }

    public QuizResult saveQuizResult(QuizResult quizResult) {
        return quizResultRepository.save(quizResult);
    }

    public Optional<QuizResult> getResultsByID(Long id) {
        return quizResultRepository.findById(id);
    }

}
