package com.example.apisearchpracticebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiSearchPracticeBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiSearchPracticeBaseApplication.class, args);
    }
}

@RestController
class ApiSearchPracticeBaseController {
    @GetMapping("hello")
    public String hello() {
        return "Hello render";
    }
}
