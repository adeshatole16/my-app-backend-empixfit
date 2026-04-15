package com.sport.platform.controller;

import com.sport.platform.entity.DietPlan;
import com.sport.platform.service.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diet")
@CrossOrigin(origins = "*")
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;

    @GetMapping("/student/{studentId}")
    public DietPlan getDietForStudent(@PathVariable Long studentId) {
        return dietPlanService.getDietPlanForStudent(studentId);
    }
}