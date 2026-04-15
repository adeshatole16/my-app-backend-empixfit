package com.sport.platform.service;

import com.sport.platform.entity.TrainingVideo;
import com.sport.platform.repository.TrainingVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainingVideoService {

    @Autowired
    private TrainingVideoRepository trainingVideoRepository;

    private final String uploadDir = "uploads/videos/";

    public TrainingVideo saveVideo(Long academyId, String title, String videoUrl) {

        // Get all academy videos sorted oldest → newest
        List<TrainingVideo> videos =
                trainingVideoRepository.findByAcademyIdOrderByUploadDateAsc(academyId);

        // If already 2 videos exist → delete oldest
        if (videos.size() >= 2) {

            TrainingVideo oldestVideo = videos.get(0);

            // Delete file from folder
            File oldFile = new File(oldestVideo.getVideoUrl());
            if (oldFile.exists()) {
                oldFile.delete();
            }

            // Delete record from DB
            trainingVideoRepository.delete(oldestVideo);
        }

        // Save new video
        TrainingVideo trainingVideo = new TrainingVideo();
        trainingVideo.setAcademyId(academyId);
        trainingVideo.setTitle(title);
        trainingVideo.setVideoUrl(videoUrl);
        trainingVideo.setUploadDate(LocalDateTime.now());

        return trainingVideoRepository.save(trainingVideo);
    }

    public List<TrainingVideo> getVideosByAcademy(Long academyId) {
        return trainingVideoRepository.findByAcademyIdOrderByUploadDateAsc(academyId);
    }
}