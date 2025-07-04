package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения.
 */
@SpringBootApplication(scanBasePackages = "com.example")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
