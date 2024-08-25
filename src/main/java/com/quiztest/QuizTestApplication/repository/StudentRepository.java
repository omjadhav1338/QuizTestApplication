package com.quiztest.QuizTestApplication.repository;

import com.quiztest.QuizTestApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
