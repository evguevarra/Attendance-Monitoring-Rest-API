package com.example.attendance.attendancemonitoring.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveRequest {
    private String empId;
    private String lastname;
    private String firstname;
    private String type;
    private String from;
    private String to;
    private String duration;
    private String status;
    private String description;
}
