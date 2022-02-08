package com.example.assessmentstudentmanagement.course;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CourseService  {



    private CourseRepository courseRepository;
    List<Course> courseList = new ArrayList<>();


    public void addCourse(String newCourseName) {
        Course newCourse = new Course(newCourseName);
          courseList.add(newCourse);
          courseRepository.saveAll(courseList);
    }

    public void deleteCourse(String courseNameToDelete) {
        courseList.remove(findCourse(courseNameToDelete));
    }

    public Course findCourse(String courseName){
        Course myCourse;
          for(Course course : courseList){
            if(course.getCourseName().equals(courseName)){
                myCourse = course;
            }
        }
        return myCourse;

    public List<Course> getCourse() {
        return courseRepository.getCourse();
    }

    public List<Course> updateCourse(List<Course> courseList) {
        return null;
    }
}




    private CourseRepository courseRepository;

    public Set<String> getCourseNames() {
        List<Course> allCourses = courseRepository.findAll();
        Set<String> coursesNames = new HashSet<>();
        for (Course course : allCourses) {
            coursesNames.add(course.getCourseName());
        }
        return coursesNames;
    }
}




