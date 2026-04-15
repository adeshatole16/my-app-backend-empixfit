package com.sport.platform.repository;

import com.sport.platform.entity.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {

    // Find diet plans based on age range and training level
    List<DietPlan> findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqualAndTrainingLevel(
            int age1,
            int age2,
            String trainingLevel
    );
}