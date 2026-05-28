package com.example.SpringSec.controller;

import com.example.SpringSec.model.Users;
import com.example.SpringSec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UserService service;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);

    @PostMapping("/register")
    public Users registeruser(@RequestBody  Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        return service.login(users);
    }


}
