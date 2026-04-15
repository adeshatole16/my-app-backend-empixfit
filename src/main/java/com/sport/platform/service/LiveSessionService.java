package com.sport.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.platform.entity.LiveSession;
import com.sport.platform.repository.LiveSessionRepository;

@Service
public class LiveSessionService {

    @Autowired
    private LiveSessionRepository liveSessionRepository;

    // Coach starts live training
    public LiveSession startLive(Long academyId, Long coachId, String streamUrl) {

        LiveSession session = new LiveSession();

        session.setAcademyId(academyId);
        session.setCoachId(coachId);
        session.setStreamUrl(streamUrl);
        session.setLive(true);

        return liveSessionRepository.save(session);
    }

    // Coach stops live training
    public void stopLive(Long academyId) {

        LiveSession session = liveSessionRepository
                .findByAcademyIdAndIsLive(academyId, true);

        if(session != null){
            session.setLive(false);
            liveSessionRepository.save(session);
        }
    }

    // Student checks if academy is live
    public LiveSession checkLive(Long academyId){

        return liveSessionRepository
                .findByAcademyIdAndIsLive(academyId, true);
    }
}