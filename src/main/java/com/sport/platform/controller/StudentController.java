package com.sport.platform.controller;
import com.sport.platform.dto.FcmTokenRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sport.platform.entity.Student;
import com.sport.platform.service.StudentService;
import com.sport.platform.dto.LoginRequest;
import com.sport.platform.service.PushNotificationService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private PushNotificationService pushNotificationService;

    // Student registration
    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @PostMapping("/login")
    public Student loginStudent(@RequestBody LoginRequest request){

    	
        return studentService.loginStudent(
                request.getUsername(),
                request.getPassword()
        );
    }
    
    @PostMapping("/save-token")
    public Student saveFcmToken(@RequestBody FcmTokenRequest request) {
        return studentService.saveFcmToken(
                request.getStudentId(),
                request.getFcmToken()
        );
    }
    
    @PostMapping("/test-notification")
    public String testNotification(@RequestParam String token) {

        return pushNotificationService.sendNotification(
                token,
                "Training Reminder",
                "Your training starts soon. Eat your diet now!"
        );
    }
    
}