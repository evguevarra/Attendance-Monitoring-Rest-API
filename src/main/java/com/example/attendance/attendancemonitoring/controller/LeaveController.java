package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.LeaveRequest;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.LeaveService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class LeaveController {

    public LeaveService leaveService;

    public LeaveController(LeaveService leaveService){
        this.leaveService = leaveService;
    }

    // Handles HTTP request for Retrieving all Leave Request
    @GetMapping("/getPendingRequest")
    public List<LeaveRequest> showPendingRequest() throws ExecutionException, InterruptedException {
        return leaveService.getPendingRequest();
    }

    // Handles HTTP request for Retrieving all Leave Request
    @GetMapping("/getApprovedRequest")
    public List<LeaveRequest> showApprovedRequest() throws ExecutionException, InterruptedException {
        return leaveService.getApprovedRequest();
    }

    // Handles HTTP request for Retrieving all Leave Request
    @GetMapping("/getDeclinedRequest")
    public List<LeaveRequest> showAllRequest() throws ExecutionException, InterruptedException {
        return leaveService.getDeclinedRequest();
    }

    // Handles HTTP request for Retrieving a certain Leave Request
    @GetMapping("/getRequest/{empId}")
    public LeaveRequest getLeaveRequest(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return leaveService.getRequest(empId);
    }

    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody LeaveRequest leaveRequest)throws InterruptedException, ExecutionException {
        return leaveService.updateStatus(leaveRequest);
    }
}
