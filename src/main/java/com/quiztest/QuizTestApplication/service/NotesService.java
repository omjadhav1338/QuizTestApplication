package com.quiztest.QuizTestApplication.service;

import com.quiztest.QuizTestApplication.entity.Notes;
import com.quiztest.QuizTestApplication.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public Notes addNotes(Notes notes){
        Notes fetchedNotes = notesRepository.fetchBySubject(notes.getSubject());
        if(fetchedNotes==null) {
            return notesRepository.save(notes);
        }
        else{
            return null;
        }
    }

    public String deleteNotes(Long id){
        notesRepository.deleteById(id);
        return "Deleted successfully";
    }

    public List<Notes> fetchNotes(){
        return notesRepository.findAll();
    }

    public Notes updateNotes(Notes notes){
        return notesRepository.save(notes);
    }

    public Notes fetchBySubject(String subject){
        return notesRepository.fetchBySubject(subject);
    }

    public String fetchSubjectByID(Long id){
        return notesRepository.fetchSubjectByID(id);
    }

}
