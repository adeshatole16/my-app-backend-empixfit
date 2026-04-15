package com.sport.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sport.platform.entity.Coach;
import com.sport.platform.repository.CoachRepository;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Coach login
    public Coach loginCoach(String username, String password){

        Coach coach = coachRepository.findByUsername(username);

        if(coach != null 
            && passwordEncoder.matches(password, coach.getPassword())){

            return coach;
        }

        return null;
    }
}