package com.example.assessmentstudentmanagement.course;

import com.example.assessmentstudentmanagement.course.Course;
import com.example.assessmentstudentmanagement.student.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CourseRepository repository){
        return args -> {
            Course WebCourse =  new Course(

                    "Web Design"

            );
            Course Backend =  new Course(

                    "Java Language"

            );


            repository.saveAll(
                    List.of(WebCourse, Backend)
            );
        };
    }
}
