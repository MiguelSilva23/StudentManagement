
package com.example.assessmentstudentmanagement.course;

import com.example.assessmentstudentmanagement.registration.RegistrationRequest;
import com.example.assessmentstudentmanagement.student.Student;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor

public class CourseService {
    
    private CourseRepository courseRepository;
    
    public void addCourse(Course newCourse) {
        courseRepository.save(newCourse);
    }

    public void deleteCourse(String deleteCourse) {

        List<Course> allCourses = courseRepository.findAll();

        for (Course course : allCourses) {
            if (course.getCourseName().equals(deleteCourse)) {
                courseRepository.delete(course);
            }
        }
    }

    public void updateCourse(String updateCourse, String newName) {
        List<Course> allCourses = courseRepository.findAll();

        for (Course course : allCourses) {
            if (course.getCourseName().equals(updateCourse)) {
                course.setCourseName(newName);
            } // probably won't work


            courseRepository.save(course);
        }
    }


    public Set<String> getCourseNames() {
        List<Course> allCourses = courseRepository.findAll();
        Set<String> coursesNames = new HashSet<>();
        for (Course course : allCourses) {
            coursesNames.add(course.getCourseName());
        }
        return coursesNames;
    }
}



