package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.QrCode;
import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.QrService;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class QrController {

    public QrService qrService;

    public QrController(QrService qrService){
        this.qrService = qrService;
    }

    @PutMapping("/updateqr")
    public String updateQr(@RequestBody QrCode qrCode)throws InterruptedException, ExecutionException {
        return qrService.updateQr(qrCode);
    }
}
