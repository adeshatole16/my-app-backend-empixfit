package com.sport.platform.service;

import com.sport.platform.entity.Attendance;
import com.sport.platform.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Mark attendance
    public Attendance markAttendance(Long studentId, Long academyId, String status){

        Attendance attendance = new Attendance();

        attendance.setStudentId(studentId);
        attendance.setAcademyId(academyId);
        attendance.setDate(LocalDate.now());
        attendance.setStatus(status);

        return attendanceRepository.save(attendance);
    }

    // Get attendance of one student
    public List<Attendance> getStudentAttendance(Long studentId){
        return attendanceRepository.findByStudentId(studentId);
    }
    
    public List<Attendance> getStudentAttendanceByMonth(Long studentId, int month, int year){

        LocalDate startDate = LocalDate.of(year, month, 1);

        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return attendanceRepository.findByStudentIdAndDateBetween(
                studentId,
                startDate,
                endDate
        );
    }

}