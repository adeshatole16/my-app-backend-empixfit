package com.sport.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sport.platform.entity.LiveSession;
import com.sport.platform.service.LiveSessionService;

@RestController
@RequestMapping("/api/live")
@CrossOrigin(origins = "*")
public class LiveSessionController {

    @Autowired
    private LiveSessionService liveSessionService;

    // Coach start live training
    @PostMapping("/start")
    public LiveSession startLive(
            @RequestParam Long academyId,
            @RequestParam Long coachId,
            @RequestParam String streamUrl
    ) {
        return liveSessionService.startLive(academyId, coachId, streamUrl);
    }

    // Coach stop live training
    @PostMapping("/stop")
    public String stopLive(@RequestParam Long academyId) {

        liveSessionService.stopLive(academyId);

        return "Live session stopped";
    }

    // Student checks live training
    @GetMapping("/check/{academyId}")
    public LiveSession checkLive(@PathVariable Long academyId){

        return liveSessionService.checkLive(academyId);
    }
}