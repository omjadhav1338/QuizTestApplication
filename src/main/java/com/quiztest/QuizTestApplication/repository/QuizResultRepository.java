package com.quiztest.QuizTestApplication.repository;

import com.quiztest.QuizTestApplication.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    List<QuizResult> findByStudentEmail(String studentEmail);
}
