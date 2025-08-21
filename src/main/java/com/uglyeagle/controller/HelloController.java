package com.uglyeagle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello, this is a public endpoint!";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "Hello, this is a protected endpoint! You are authenticated.";
    }

}
