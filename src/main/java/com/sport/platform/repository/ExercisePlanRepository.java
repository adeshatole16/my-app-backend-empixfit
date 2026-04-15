package com.sport.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sport.platform.entity.ExercisePlan;

public interface ExercisePlanRepository 
        extends JpaRepository<ExercisePlan, Long> {

    List<ExercisePlan> findByLevel(String level);
}