package com.quiztest.QuizTestApplication.repository;

import com.quiztest.QuizTestApplication.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotesRepository extends JpaRepository<Notes,Long> {

    @Query("SELECT n FROM Notes n WHERE n.subject = :subject")
    Notes fetchBySubject(@Param("subject") String subject);

    @Query("SELECT n.subject FROM Notes n WHERE n.id = :id")
    String fetchSubjectByID(@Param("id") Long id);
}
