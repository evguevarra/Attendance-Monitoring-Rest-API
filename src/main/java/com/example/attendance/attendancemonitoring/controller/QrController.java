package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.QrCode;
import com.example.attendance.attendancemonitoring.service.QrService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getqr/{qrId}")
    public QrCode getQr(@PathVariable String qrId)throws InterruptedException, ExecutionException {
        return qrService.getQr(qrId);
    }
}
