package com.example.assessmentstudentmanagement.course;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

  private CourseController courseController;

  @Mock
  private CourseService courseService;
  @Mock
  private Model model;
  //field injection

  @Test
  void getStudentCourse() {
    Set<String> courses = new HashSet<>();
    Mockito.when(courseService.getCourseNames()).thenReturn(courses);
    Mockito.when(model.addAttribute("courses", courses)).thenReturn(model);
    assertEquals("course_list", courseController.getStudentCourse(model));
  }

  @Test
  void selectedCourse() {
    String output = courseController.selectedCourse();
    assertEquals("course_selected",output);
  }

  @Test
  void getLogoutPage() {
    String output = courseController.getLogoutPage();
    assertEquals("logout_page",output);
  }

  //if we use the @BeforeEach, the init() method is run before each test.
  // so in this case here the courseController is set every time a new test is run
  //in a test class with 10 tests, the init() method is run 10 times
  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    courseController = new CourseController(courseService);
  }

  @BeforeAll
  static void setUp() {

  }

  //if we use @BeforeAll, the setUp() method is run only once, when the class is loaded and before any test.
  //in a test class with 10 tests, the setUp method is run once
  /*@BeforeAll
  static void setUp() {
    //in this case the courseController would need to be static
    courseController = new CourseController(null);
  }*/
}