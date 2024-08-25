package com.quiztest.QuizTestApplication.service;

import com.quiztest.QuizTestApplication.entity.Student;
import com.quiztest.QuizTestApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/omjadhav1338/QuizTestApplication.git
//git push -u origin main



@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> fetchAllStudents(){return studentRepository.findAll();}
    public Student fetchStudentByID(long id){return studentRepository.findById(id).orElse(null);}
    public Student insert(Student student){return studentRepository.save(student);}
    public void delete(long id){studentRepository.deleteById(id);}


}
