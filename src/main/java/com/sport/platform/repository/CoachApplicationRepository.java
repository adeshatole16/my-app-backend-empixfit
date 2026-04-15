package com.sport.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sport.platform.entity.CoachApplication;

public interface CoachApplicationRepository extends JpaRepository<CoachApplication, Long> {
}