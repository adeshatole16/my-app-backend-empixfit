package com.sport.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.platform.entity.ExercisePlan;
import com.sport.platform.repository.ExercisePlanRepository;

@Service
public class ExercisePlanService {

    @Autowired
    private ExercisePlanRepository repository;

    public List<ExercisePlan> getPlansByLevel(String level) {
        return repository.findByLevel(level);
    }
}