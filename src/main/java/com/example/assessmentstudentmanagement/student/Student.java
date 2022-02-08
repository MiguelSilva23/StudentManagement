package com.example.assessmentstudentmanagement.student;

import com.example.assessmentstudentmanagement.course.Course;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Student implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    //giving the Student some necessary fields
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean enabled = false;


    //this field comes from our enum
    //.........................................................................................
    @Enumerated(EnumType.STRING)
    private Role studentRole;

    //Many students can be enrolled in one course
    @ManyToOne
    //.........................................................................................
    @JoinColumn(nullable = false,
            name = "course_name"
    )
    private Course course;


    //We have no "noargsconstructor" or "allargsconstructor" because there should be no student
    //without first name, lastname, email, password, and role. However, it is not necessary to have
    // an ID because it is going to be generated from the primary key, and not from the person.

    //also have a Constructor without ID
    public Student(String firstName, String lastName, String email, String password, Role studentRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.studentRole = studentRole;
    }


//.........................................................................................
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(studentRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    //.........................................................................................
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
