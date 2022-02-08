package com.example.assessmentstudentmanagement.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
   return "course_list";
 }

 @GetMapping("/logout")
  public String getLogoutPage(){
    return "logout_page";
 }
}
