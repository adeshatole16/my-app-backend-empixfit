package com.sport.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sport.platform.entity.Coach;
import com.sport.platform.service.CoachService;
import java.util.Map;

@RestController
@RequestMapping("/api/coaches")
@CrossOrigin("*")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCoach(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Coach coach = coachService.loginCoach(username, password);

        if (coach == null) {
            return ResponseEntity.status(401).body(Map.of(
                "status", "error",
                "message", "Invalid username or password"
            ));
        }

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "role", "COACH",
            "name", coach.getName(),
            "username", coach.getUsername(),
            "level", coach.getLevel() != null ? coach.getLevel() : "BEGINNER"
            // never return password
        ));
    }
}