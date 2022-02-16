package com.example.attendance.attendancemonitoring.controller;

import com.example.attendance.attendancemonitoring.entity.User;
import com.example.attendance.attendancemonitoring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    public UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user)throws InterruptedException, ExecutionException {
        return userService.createUser(user);
    }

    @GetMapping("/get/{empId}")
    public User getUser(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return userService.getUser(empId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user)throws InterruptedException, ExecutionException {
        return userService.updateUser(user);
    }

    @PutMapping("/delete")
    public String deleteUser(@RequestParam String empID)throws InterruptedException, ExecutionException {
        return userService.deleteUser(empID);
    }

    @GetMapping("/ctest")
    public ResponseEntity<String> testGetEndPoint(){
        return ResponseEntity.ok("Test Get Endpoint is working");
    }

    @GetMapping("/getAll")
    public List<User> showAllEmployees() throws ExecutionException, InterruptedException {
        return userService.getAllEmployeeList();
    }

    @PostMapping("/profile/pic")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        //logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return userService.upload(multipartFile);
    }



}
