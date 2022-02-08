package com.example.assessmentstudentmanagement.course;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
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

            Course Python =  new Course(

                    "Python Language"

            );

            repository.saveAll(
                    List.of(WebCourse, Backend, Python)
            );
        };
    }
}
