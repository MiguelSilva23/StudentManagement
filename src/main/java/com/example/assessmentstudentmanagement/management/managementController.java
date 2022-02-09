package com.example.assessmentstudentmanagement.management;

import com.example.assessmentstudentmanagement.course.Course;
import com.example.assessmentstudentmanagement.course.CourseService;
import com.example.assessmentstudentmanagement.student.Student;
import com.example.assessmentstudentmanagement.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class managementController {

    private CourseService courseService;
    private StudentService studentService;

    //Management Home Page
    @GetMapping("/management")
    String managementHomePage(){return "management_personal_page";}

    //Management Courses
    @GetMapping("/management/courses")
    String managementCoursesPage(){return "course_list_page";}

    @GetMapping("/management/courses/add")
    String addCourse(){return "add_course";}

    @GetMapping("/management/courses/delete")
    String deleteCourse(){return "delete_course";}

    @GetMapping("/management/courses/update")
    String updateCourse(){return "update_course";}

    @PostMapping("/management/courses/add")
    String addCourse2(@ModelAttribute Course newNameCourse){

        courseService.addCourse(newNameCourse);

        return "redirect:/management/courses";
    }

    @PostMapping("/management/courses/delete")
    String addCourse2(@ModelAttribute String newNameCourse){

        courseService.deleteCourse(newNameCourse);

        return "redirect:/management/courses";
    }

    @PostMapping("/management/courses/update")
    String addCourse2(@ModelAttribute String findCourse, @ModelAttribute String newNameCourse ){

        courseService.updateCourse(findCourse, newNameCourse);

        return "redirect:/management/courses";
    }


    // Management Students
    @GetMapping("/management/students/")
    String listOfStudents(Model model){

       model.addAttribute("listOfStudents",studentService.getStudentList());

        return "management_students";
    }

    @GetMapping("/management/students/add")
    String addStudent(){return "add_students";}

    @GetMapping("/management/students/delete")
    String deleteStudent(){return "delete_students";}

    @GetMapping("/management/students/update")
    String updateStudent(){return "update_students";}

    @PostMapping("/management/students/add")
    String addStudent2(@ModelAttribute Student newStudent){

        studentService.addStudent(newStudent);

        return "redirect:/management/students";
    }

    @PostMapping("/management/students/delete")
    String deleteStudent2(@ModelAttribute String email){

        studentService.removeStudent(email);
        return "redirect:/management/students";
    }

    @PostMapping("/management/students/update")
    String updateStudent2(@ModelAttribute String email, @ModelAttribute String newName){

        studentService.updateStudent(email,newName);

        return "redirect:/management/students";
    }





}
