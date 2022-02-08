package com.example.assessmentstudentmanagement.student;

import com.example.assessmentstudentmanagement.registration.token.ConfirmationToken;
import com.example.assessmentstudentmanagement.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;

    private final static String  STUDENT_NOT_FOUND_MSG =
            "student with email %s not found";

    //Implementation of PasswordEncoder that uses the BCrypt strong hashing function. Clients can optionally supply a "strength"
    // (a.k.a. log rounds in BCrypt) and a SecureRandom instance. The larger the strength parameter the more work will have to be done (exponentially) to hash the passwords.
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;



    public String signUpStudent(Student student) {
        boolean studentExists = studentRepository
                .findByEmail(student.getEmail())
                .isPresent();
        if(studentExists){
            throw new IllegalStateException("email is taken");
        }

        //Consider defining a bean of type BCryptPasswordEncoder' in security configuration.
        String encodedPassword = bCryptPasswordEncoder.encode(student.getPassword());

        student.setPassword(encodedPassword);

        studentRepository.save(student);

        // Normally we use other package than UUID to generate a token
        String token =  UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                student
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;

    }

    public int enableStudent(String email) {
        return studentRepository.enableStudent(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepository.findByEmail(email)
                .orElseThrow(
                        () ->
                                new UsernameNotFoundException(
                                        String.format(STUDENT_NOT_FOUND_MSG,email)
                                )
                );
    }

    public Student findByEmail(String email){

        Optional<Student> myStudent = studentRepository.findByEmail(email);

        return myStudent.get();
    }

    public boolean matchPassword(String logRequest, String passwordFromDB){

        return bCryptPasswordEncoder.matches(logRequest,passwordFromDB);
    }



}
