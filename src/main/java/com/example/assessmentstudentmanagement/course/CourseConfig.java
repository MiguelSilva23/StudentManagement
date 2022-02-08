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

            Course Frontend =  new Course(

                    "Javascript Language"

            );

            Course Marketing =  new Course(

                    "Marketing"

            );

            repository.saveAll(
                    List.of(WebCourse, Backend, Frontend, Marketing)
            );
        };
    }
}
