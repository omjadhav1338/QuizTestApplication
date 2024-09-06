package com.quiztest.QuizTestApplication.service;

import com.quiztest.QuizTestApplication.entity.Student;
import com.quiztest.QuizTestApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("oj740453@gmail.com");

        javaMailSender.send(message);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
