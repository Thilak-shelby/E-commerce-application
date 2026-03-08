package com.example.e_commerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello authenticated user!";
    }
}