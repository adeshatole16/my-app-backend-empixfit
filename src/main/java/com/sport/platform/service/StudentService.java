package com.sport.platform.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sport.platform.entity.Student;
import com.sport.platform.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register student
    public Student registerStudent(Student student) {
    	 System.out.println("Student saving to database...");
        LocalDate startDate = LocalDate.now();
        student.setPlanStartDate(startDate);

        if(student.getPlanType().equalsIgnoreCase("3month")){
            student.setPlanEndDate(startDate.plusMonths(3));
        } else if(student.getPlanType().equalsIgnoreCase("6month")){
            student.setPlanEndDate(startDate.plusMonths(6));
        }

        // Generate username
        String username = student.getName().replaceAll(" ", "").toLowerCase()
                + (int)(Math.random()*1000);

        // Generate password
        String password = "sp" + (int)(Math.random()*10000);

        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
 
        student.setRole("STUDENT");
        
        Student savedStudent = studentRepository.save(student);

        // Send email
        emailService.sendCredentials(
                student.getEmail(),
                username,
                password
        );

        return savedStudent;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student loginStudent(String username, String password){

        Student student = studentRepository.findByUsername(username);

        if(student != null && passwordEncoder.matches(password, student.getPassword())){
            return student;
        }

        return null;
    }
    
    public Student saveFcmToken(Long studentId, String fcmToken) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            student.setFcmToken(fcmToken);
            return studentRepository.save(student);
        }

        return null;
    }
}