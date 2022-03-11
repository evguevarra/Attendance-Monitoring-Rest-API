package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.LeaveRequest;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.LeaveService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class LeaveController {

    public LeaveService leaveService;

    public LeaveController(LeaveService leaveService){
        this.leaveService = leaveService;
    }



    @GetMapping("/getAllRequest")
    public List<LeaveRequest> showAllRequest() throws ExecutionException, InterruptedException {
        return leaveService.getAllRequest();
    }
}
