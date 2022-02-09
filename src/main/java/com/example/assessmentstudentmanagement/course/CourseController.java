package com.example.assessmentstudentmanagement.course;


import com.example.assessmentstudentmanagement.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Controller
public class CourseController {


    private CourseService courseService;

    //let´s firstly add method to a redirect user to this specific page, specific time leaf or html page
    // we need to return string with the name of our html page

    @GetMapping("/course_list")
    public String getStudentCourse(Model model) {
        Set<String> courses = courseService.getCourseNames();

        model.addAttribute("courses", courses);

        return "course_list";
    }

    @PostMapping("/course_list")
    public String selectedCourse() {
        return "course_selected";

    }


    @GetMapping("/logout")
    public String getLogoutPage(){
        return "logout_page";
    }


}