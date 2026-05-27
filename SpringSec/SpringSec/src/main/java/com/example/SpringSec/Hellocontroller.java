package com.example.SpringSec;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/")
public class Hellocontroller   {

    @GetMapping("home")
    public String greet(HttpServletRequest request){
        return request.getSession().getId();
    }


}
