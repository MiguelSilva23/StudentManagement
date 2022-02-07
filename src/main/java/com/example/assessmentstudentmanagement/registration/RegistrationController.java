package com.example.assessmentstudentmanagement.registration;

import com.example.assessmentstudentmanagement.registration.token.ConfirmationToken;
import com.example.assessmentstudentmanagement.student.Student;
import com.example.assessmentstudentmanagement.student.StudentRepository;
import lombok.AllArgsConstructor;
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
    private ConfirmationToken confirmationToken;
    private StudentRepository studentRepository;


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        if (registrationService.confirmToken(token).equals("confirmed")) {

            return "personal_page";

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
                        regStudent.getPassword(),
                        regStudent.getCourse().getCourseName()
                        )
        );

        model.addAttribute("studentName", regStudent.getFirstName());

        return token == null ? "error_page" : "personal_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Student logStudent){


        Student myLoginStudent = studentRepository.findByEmail(logStudent.getEmail());

        if(myLoginStudent == null){
            return "error_page";
        }
        else if(!myLoginStudent.getPassword().equals(logStudent.getPassword())){
            return "error_page";
        }

        return "personal_page"; // "redirect:/personal"

    }
}
