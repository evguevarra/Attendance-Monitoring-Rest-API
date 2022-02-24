package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.Attendance;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.AttendanceService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class AttendanceController {

    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @GetMapping("/getAttendance")
    public List<Attendance> showAttendance() throws ExecutionException, InterruptedException {
        return attendanceService.getAttendance();
    }
}
