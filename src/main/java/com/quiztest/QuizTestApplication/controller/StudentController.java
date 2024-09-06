package com.quiztest.QuizTestApplication.controller;

import com.quiztest.QuizTestApplication.config.SecurityConfig;
import com.quiztest.QuizTestApplication.dto.ResetPasswordRequest;
import com.quiztest.QuizTestApplication.entity.MailRequest;
import com.quiztest.QuizTestApplication.entity.Student;
import com.quiztest.QuizTestApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/get-students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/save-student")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/get-student-by-id")
    public Optional<Student> getStudentById(@RequestParam Long id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/delete-student")
    public String deleteStudent(@RequestParam Long id){
        return studentService.deleteStudent(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(student.getEmail(), student.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: Invalid email or password");
        }
    }

    @GetMapping("/student")
    public Optional<Student> getStudentByEmail(@RequestParam String email) {
        System.out.println(email.getClass().getTypeName());
        System.out.println(email);
        Optional<Student> student = studentService.findByEmail(email);
        System.out.println(student);
        System.out.println(student.isPresent());
        return student;
    }

    @PostMapping("/sendsignupmail")
    public String sendSignupMail(@RequestBody MailRequest mailRequest){
        System.out.println(mailRequest.getMail());
        studentService.sendMail(mailRequest.getMail(), mailRequest.getSubject(), mailRequest.getBody());
        return "Mail sent successfully";
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestBody MailRequest mailRequest) {
        System.out.println(mailRequest.getMail());
        studentService.sendMail(mailRequest.getMail(), mailRequest.getSubject(), mailRequest.getBody());
        return "Mail sent successfully!";
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        Optional<Student> studentOpt = studentService.findByEmail(request.getEmail());

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setPassword(studentService.encodePassword(request.getNewPassword()));  // Encode new password
            studentService.updateStudent(student);  // Save the updated password
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Email not found\"}");
        }
    }
}