package com.example.assessmentstudentmanagement.course;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class CourseController {


  @GetMapping("/course_list_page")
  public String getStudentCourse(Model model) {

    //model.addAttribute("courseList", new Course());

    return "course_selected";
  }

  @PostMapping("/course_list_page")
  public String selectedCourse(@ModelAttribute Course course, Model model) {

    model.addAttribute("courseSelected", course.getCourseName());

    return "course_selected";

  }


 @GetMapping("/logout")
  public String getLogoutPage(Model model){

   model.addAttribute("logoutRequest", new Student());
    return "logout_page";
 }

}
