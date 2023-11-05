package com.example.demo.controller;


import com.example.demo.domain.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "hello")
public class HelloController {
    @GetMapping()
    public Hello get() {
        return new Hello(0, "Hello Spring Boot! Hi");
    }
}