package com.quiztest.QuizTestApplication.controller;

import com.quiztest.QuizTestApplication.entity.Notes;
import com.quiztest.QuizTestApplication.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/save-notes")
    public Notes saveNotes(@RequestBody Notes notes){
        return notesService.addNotes(notes);
    }

    @DeleteMapping("/delete-notes")
    public String deleteNotes(@RequestParam Long id){
        return notesService.deleteNotes(id);
    }

    @GetMapping("/all-notes")
    public List<Notes> fetchNotes(){
        return notesService.fetchNotes();
    }

    @PutMapping("/update-notes")
    public Notes updateNotesBySubject(@RequestBody Notes notes){
        return notesService.updateNotes(notes);
    }

    @GetMapping("/fetch-by-subject")
    public Notes fetchBySubject(@RequestParam String subject){
        return notesService.fetchBySubject(subject);
    }

    @GetMapping("/get-subject-by-id")
    public String fetchSubjectByID(@RequestParam Long id){
        return notesService.fetchSubjectByID(id);
    }
}
