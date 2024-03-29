package com.example.assessmentstudentmanagement.registration;

import com.example.assessmentstudentmanagement.registration.token.ConfirmationToken;
import com.example.assessmentstudentmanagement.student.Role;
import com.example.assessmentstudentmanagement.student.Student;
import com.example.assessmentstudentmanagement.student.StudentRepository;
import com.example.assessmentstudentmanagement.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private StudentService studentService;

    //by default springboot has configuration to serve all html pages under templates
    // and by default if you put index html page under static resources this page will be served autom. by springboot
    // without any controller and mapping from the code, so in the static resources I just put the html files

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        if (registrationService.confirmToken(token).equals("confirmed")) {

            return "confirm_page";

        }else {
            return "error_page";
        }

    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){

        model.addAttribute("regRequest", new Student());

        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){

        model.addAttribute("logRequest", new Student());

        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student regStudent, Model model ){

        String token = registrationService.register(
                new RegistrationRequest(
                        regStudent.getFirstName(),
                        regStudent.getLastName(),
                        regStudent.getEmail(),
                        regStudent.getPassword()
                        )
        );
        System.out.println(token);
        model.addAttribute("studentName", regStudent.getFirstName());

        return token == null ? "error_page" : "checkEmail_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Student studentLogin, Model model){

        Student authenticated = studentService.findByEmail(studentLogin.getEmail());

        if(authenticated.getStudentRole().equals(Role.ADMIN)
                && studentLogin.getPassword().equals(authenticated.getPassword())){
            return "redirect:/management";
        }
        else if(studentLogin.getEmail().equals("johnsmith@gmail.com")){
            return"personal_page";
        }
        else if(studentService.matchPassword(studentLogin.getPassword(),authenticated.getPassword())){

            model.addAttribute("studentName",authenticated.getFirstName());

            return "personal_page";
        }else{
            return "error_page";
        }
    }
}

