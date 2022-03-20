package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.Attendance;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.AttendanceService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/get-certain-all-attendance")
    public List<Attendance> showCertainAllAttendance() throws ExecutionException, InterruptedException {
        return attendanceService.getAllCertainAttendance();
    }

    @GetMapping("/get-attendance/{empId}")
    public Map<String, Object> showAllAttendance(@PathVariable String empId) throws ExecutionException, InterruptedException {
        return attendanceService.getAllAttendance(empId);
    }

    @PostMapping("/addAttendance")
    public String addAttendance(@RequestBody Attendance attendance)throws InterruptedException, ExecutionException {
        return attendanceService.addAttendance(attendance);
    }

    @PutMapping("/updateAttendance")
    public String updateAttendance(@RequestBody Attendance attendance)throws InterruptedException, ExecutionException {
        return attendanceService.updateAttendance(attendance);
    }

    @GetMapping("/getUserAttendance/{empId}")
    public Attendance getUserAttendance(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return attendanceService.getUserAttendance(empId);
    }

    @GetMapping("/countTimedIn")
    public String countEmployees() throws ExecutionException, InterruptedException {
        return attendanceService.getTotalTimedIn();
    }

    @GetMapping("/checkExistence/{empId}")
    public String checkIfExist(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return attendanceService.checkIfExist(empId);
    }

}
