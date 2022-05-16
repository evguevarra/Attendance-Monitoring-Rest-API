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

    @DeleteMapping("/delete/{empId}")
    public String deleteUser(@PathVariable String empId)throws InterruptedException, ExecutionException {
        return userService.deleteUser(empId);
    }

    @GetMapping("/getAll")
    public List<User> showAllEmployees() throws ExecutionException, InterruptedException {
        return userService.getAllEmployeeList();
    }

    @GetMapping("/countEmployees")
    public String countEmployees() throws ExecutionException, InterruptedException {
        return userService.getTotalEmployees();
    }

    @PostMapping("/profile/pic")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        return userService.upload(multipartFile);
    }



}
