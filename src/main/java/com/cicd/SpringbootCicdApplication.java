package com.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootCicdApplication {
	
	@GetMapping("/")
    public String home() {
        return "Spring Boot CI/CD Pipeline Running!";
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCicdApplication.class, args);
	}

}
