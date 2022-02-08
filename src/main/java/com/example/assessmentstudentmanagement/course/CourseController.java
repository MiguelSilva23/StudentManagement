package com.example.assessmentstudentmanagement.course;

import com.example.assessmentstudentmanagement.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class CourseController {

  private CourseService courseService;

  @GetMapping("/course list")
  public String getCourseListPage(Model model){

    model.addAttribute("regRequest", new Student());

    return "course_list_page";
  }
}
