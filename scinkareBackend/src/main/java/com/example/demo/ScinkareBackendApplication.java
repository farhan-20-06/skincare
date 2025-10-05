package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.scinkare", "com.example.demo"})
@EnableJpaRepositories(basePackages = "com.scinkare.repository")
@EntityScan(basePackages = "com.scinkare.model")
public class ScinkareBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScinkareBackendApplication.class, args);
    }
}