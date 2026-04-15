package com.sport.platform.repository;

import com.sport.platform.entity.TrainingVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingVideoRepository extends JpaRepository<TrainingVideo, Long> {

    // Get videos of an academy (oldest → newest)
    List<TrainingVideo> findByAcademyIdOrderByUploadDateAsc(Long academyId);

}