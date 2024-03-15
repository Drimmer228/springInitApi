package com.example.apisearchpracticebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.runApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
class ApiSearchPracticeBaseApplication 

fun main(args: Array<String>){
	runApplication<ApiSearchPracticeBaseApplication>(*args);
}

@RestController
class ApiSearchPracticeBaseController{
	@GetMapping("hello")
	fun hello() : String{
		return "Hello render"
	}
}
