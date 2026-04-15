package com.sport.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sport.platform.entity.ExercisePlan;
import com.sport.platform.service.ExercisePlanService;

@RestController
@RequestMapping("/api/exercise")
@CrossOrigin("*")
public class ExercisePlanController {

    @Autowired
    private ExercisePlanService service;

    // Coach view (read-only)
    @GetMapping("/coach")
    public List<ExercisePlan> getCoachPlans(@RequestParam String level) {
        return service.getPlansByLevel(level);
    }
}