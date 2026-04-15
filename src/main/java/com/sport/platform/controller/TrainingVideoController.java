package com.sport.platform.controller;

import com.sport.platform.entity.TrainingVideo;
import com.sport.platform.service.TrainingVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class TrainingVideoController {

    @Autowired
    private TrainingVideoService trainingVideoService;

    private final String uploadDir = "uploads/videos/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(
            @RequestParam Long academyId,
            @RequestParam String title,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Create folder if not exists
            File folder = new File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Unique file name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = "http://localhost:8080/videos/" + fileName;

            // Save file to folder
            file.transferTo(new File(filePath));

            // Save DB record
            TrainingVideo savedVideo =
                    trainingVideoService.saveVideo(academyId, title, filePath);

            return ResponseEntity.ok(savedVideo);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Video upload failed");
        }
    }

    @GetMapping("/{academyId}")
    public List<TrainingVideo> getVideosByAcademy(@PathVariable Long academyId) {
        return trainingVideoService.getVideosByAcademy(academyId);
    }
}