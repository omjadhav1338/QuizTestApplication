package com.quiztest.QuizTestApplication.controller;

import com.quiztest.QuizTestApplication.entity.Student;
import com.quiztest.QuizTestApplication.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    //RestAPIs
    @GetMapping("/students")
    public List<Student> fetchStudent(){
        return studentService.fetchAllStudents();
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student){
        return studentService.insert(student);
    }

}
