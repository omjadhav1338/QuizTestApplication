package com.quiztest.QuizTestApplication.repository;

import com.quiztest.QuizTestApplication.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    List<QuizResult> findByStudentEmail(String studentEmail);

}
