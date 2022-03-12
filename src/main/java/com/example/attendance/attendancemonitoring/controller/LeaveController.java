package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.LeaveRequest;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.LeaveService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class LeaveController {

    public LeaveService leaveService;

    public LeaveController(LeaveService leaveService){
        this.leaveService = leaveService;
    }

    // Handles HTTP request for Retrieving all Leave Request
    @GetMapping("/getAllRequest")
    public List<LeaveRequest> showAllRequest() throws ExecutionException, InterruptedException {
        return leaveService.getAllRequest();
    }

    // Handles HTTP request for Retrieving a certain Leave Request
    @GetMapping("/getRequest/{empId}")
    public LeaveRequest getLeaveRequest(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return leaveService.getRequest(empId);
    }
}
