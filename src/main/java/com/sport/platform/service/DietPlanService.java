package com.sport.platform.service;

import com.sport.platform.entity.DietPlan;
import com.sport.platform.entity.Student;
import com.sport.platform.repository.DietPlanRepository;
import com.sport.platform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietPlanService {

    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Autowired
    private StudentRepository studentRepository;

    public DietPlan getDietPlanForStudent(Long studentId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if (student == null) {
            return null;
        }

        List<DietPlan> plans =
                dietPlanRepository.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqualAndTrainingLevel(
                        student.getAge(),
                        student.getAge(),
                        student.getExperience()
                );

        if (plans.isEmpty()) {
            return null;
        }

        return plans.get(0);
    }
}