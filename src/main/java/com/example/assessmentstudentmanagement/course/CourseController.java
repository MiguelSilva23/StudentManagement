package com.example.assessmentstudentmanagement.course;

import com.example.assessmentstudentmanagement.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class CourseController {

  private CourseService courseService;

  @GetMapping("/course list")
 public String getCourseList(Model model){
//
//    List<Course> courses =
//    System.out.println();
//
   return "course_list_page";
 }

 @GetMapping("/logout")
  public String getLogoutPage(Model model){

   model.addAttribute("logoutRequest", new Student());
    return "logout_page";
 }
}
