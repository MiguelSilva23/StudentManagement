package com.example.assessmentstudentmanagement.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {
    @Bean
    CommandLineRunner commandLineRunner2(
            StudentRepository repository) {
        return args -> {
            Student ADMIN = new Student(
                    "Marie", "Smith",
                    "mariesmith@gmail.com",
                    "1Adminadmin",
                    Role.ADMIN
            );
            Student STUDENT = new Student(
                    "John", "Smith",
                    "johnsmith@gmail.com",
                    "1Student",
                    Role.STUDENT
            );
            repository.saveAll(
                    List.of(ADMIN,STUDENT));
        };
    }
}
