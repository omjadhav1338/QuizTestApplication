package com.quiztest.QuizTestApplication.repository;

import com.quiztest.QuizTestApplication.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository <Quiz, Long> {

    @Query(value = "SELECT q FROM Quiz q WHERE q.subject = :subject ORDER BY RAND() LIMIT :limit")
    List<Quiz> findBySubjectWithLimit(@Param("subject") String subject, @Param("limit") int limit);

    @Query(value = "SELECT * FROM Quiz q WHERE q.subject = :subject ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Quiz> findRandomQuizzesBySubject(@Param("subject") String subject, @Param("limit") int limit);

    @Query(value = "SELECT * FROM Quiz q WHERE q.subject = :subject LIMIT :limit", nativeQuery = true)
    List<Quiz> findQuizzesBySubject(@Param("subject") String subject, @Param("limit") int limit);


//    List<Quiz> findResultDetailById();
}
