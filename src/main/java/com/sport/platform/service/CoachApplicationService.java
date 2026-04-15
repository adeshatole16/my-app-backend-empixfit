package com.sport.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sport.platform.entity.Coach;
import com.sport.platform.entity.CoachApplication;
import com.sport.platform.repository.CoachApplicationRepository;
import com.sport.platform.repository.CoachRepository;

@Service
public class CoachApplicationService {

    @Autowired
    private CoachApplicationRepository repository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    // 1️⃣ Apply coach
    public CoachApplication apply(CoachApplication application) {
        application.setStatus("PENDING");
        return repository.save(application);
    }

    // 2️⃣ Get all applications (Admin)
    public List<CoachApplication> getAllApplications() {
        return repository.findAll();
    }

    // 3️⃣ Approve coach
    public String approveCoach(Long id) {

        CoachApplication app = repository.findById(id).orElse(null);

        if (app == null) {
            return "Application not found";
        }

        // Create Coach
        Coach coach = new Coach();

        coach.setName(app.getFullName());
        coach.setEmail(app.getEmail());
        coach.setPhone(app.getPhone());
        coach.setSport(app.getSport());
        coach.setLocation(app.getLocation());

        // Generate username
        String username = app.getFullName().replaceAll(" ", "").toLowerCase()
                + (int)(Math.random()*1000);

        // Generate password
        String rawPassword = "coach" + (int)(Math.random()*10000);

        coach.setUsername(username);
        coach.setPassword(passwordEncoder.encode(rawPassword));
        coach.setRole("COACH");

        coachRepository.save(coach);

        // Delete application after approval
        repository.delete(app);

        // Send credentials
        emailService.sendCredentials(
                coach.getEmail(),
                username,
                rawPassword
        );

        return "Coach approved successfully";
    }
}