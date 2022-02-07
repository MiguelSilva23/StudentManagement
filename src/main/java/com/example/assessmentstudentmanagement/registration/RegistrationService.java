package com.example.assessmentstudentmanagement.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        //email should be validated
        boolean isValidEmail = emailValidator
                .test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(
                    "email is not valid"
            );
        }




    }
}




