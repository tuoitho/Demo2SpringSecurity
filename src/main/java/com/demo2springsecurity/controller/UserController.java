package com.demo2springsecurity.controller;

import com.demo2springsecurity.entiy.UserInfo;
import com.demo2springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("")
    public String home() {
        return "Welcome to the home page!";
    }
    @GetMapping("/a")
    public String a() {
        return "Welcome to the a page!";
    }

    @PostMapping("/user/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }


}