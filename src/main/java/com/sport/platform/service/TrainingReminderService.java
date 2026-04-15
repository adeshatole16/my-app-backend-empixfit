package com.sport.platform.service;
import com.sport.platform.service.PushNotificationService;

import com.sport.platform.entity.DietPlan;
import com.sport.platform.entity.Student;
import com.sport.platform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.time.LocalDate;

@Service
public class TrainingReminderService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DietPlanService dietPlanService;
    
    @Autowired
    private PushNotificationService pushNotificationService;

    @Scheduled(cron = "0 * * * * *") // every 1 minute
    public void sendTrainingReminder() {

        List<Student> students = studentRepository.findAll();

        LocalTime now = LocalTime.now().withSecond(0).withNano(0);
        String today = LocalDate.now().toString();

        for (Student student : students) {

            if (student.getTrainingTime() == null || student.getTrainingTime().isEmpty()) {
                continue;
            }

            try {
                LocalTime trainingTime = LocalTime.parse(student.getTrainingTime());

                LocalTime reminderTime = trainingTime.minusMinutes(30);

                if (now.equals(reminderTime)) {

                    if (student.getFcmToken() == null || student.getFcmToken().isEmpty()) {
                        continue;
                    }

                    DietPlan dietPlan = dietPlanService.getDietPlanForStudent(student.getId());

                    String title = "Training Reminder";
                    String body;

                    if (dietPlan != null) {
                        body = "Your training starts at " + student.getTrainingTime()
                                + ". Diet: " + dietPlan.getDietText();
                    } else {
                        body = "Your training starts at " + student.getTrainingTime()
                                + ". Get ready for your session!";
                    }

                    pushNotificationService.sendNotification(
                            student.getFcmToken(),
                            title,
                            body
                    );

                    System.out.println("Push notification sent to: " + student.getName());
                }
            } catch (Exception e) {
                System.out.println("Invalid training time for student: " + student.getName());
            }
        }
    }
}