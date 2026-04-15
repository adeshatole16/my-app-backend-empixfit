package com.sport.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sport.platform.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    Coach findByUsername(String username);
}