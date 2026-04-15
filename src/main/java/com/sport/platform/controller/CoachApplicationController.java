package com.sport.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sport.platform.entity.CoachApplication;
import com.sport.platform.service.CoachApplicationService;

@RestController
@RequestMapping("/api/coach")
@CrossOrigin("*")
public class CoachApplicationController {

    @Autowired
    private CoachApplicationService service;

    // Apply coach
    @PostMapping("/apply")
    public CoachApplication apply(@RequestBody CoachApplication application) {
        return service.apply(application);
    }

    // Admin view all applications
    @GetMapping("/applications")
    public List<CoachApplication> getAllApplications() {
        return service.getAllApplications();
    }

    // Admin approve coach
    @PostMapping("/approve/{id}")
    public String approveCoach(@PathVariable Long id) {
        return service.approveCoach(id);
    }
}