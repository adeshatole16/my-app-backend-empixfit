package com.sport.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sport.platform.entity.LiveSession;

public interface LiveSessionRepository extends JpaRepository<LiveSession, Long> {

    LiveSession findByAcademyIdAndIsLive(Long academyId, boolean isLive);

}