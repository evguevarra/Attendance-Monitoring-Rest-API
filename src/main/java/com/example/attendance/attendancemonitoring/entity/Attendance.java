package com.example.attendance.attendancemonitoring.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Attendance {


    private String empId;
    private String firstName;
    private String lastName;
    private String timeIn;
    private String timeOut;

}
