package com.sport.platform.controller;

import com.sport.platform.entity.Attendance;
import com.sport.platform.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Coach marks attendance
    @PostMapping("/mark")
    public Attendance markAttendance(
            @RequestParam Long studentId,
            @RequestParam Long academyId,
            @RequestParam String status
    ){
        return attendanceService.markAttendance(studentId, academyId, status);
    }

    // Get attendance for student dashboard
    @GetMapping("/student/{studentId}")
    public List<Attendance> getStudentAttendance(
            @PathVariable Long studentId,
            @RequestParam int month,
            @RequestParam int year
    ) {

        return attendanceService.getStudentAttendanceByMonth(
                studentId,
                month,
                year
        );
    }

}