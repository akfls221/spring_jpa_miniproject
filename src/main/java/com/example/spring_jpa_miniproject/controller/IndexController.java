package com.example.spring_jpa_miniproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/hello")
    public String hello() {
        return "this is test Controller";
    }
}
