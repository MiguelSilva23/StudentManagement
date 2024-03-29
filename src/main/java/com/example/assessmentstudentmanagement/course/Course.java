package com.example.assessmentstudentmanagement.course;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//annotate any field with @Getter and/or @Setter, to let lombok generate the default getter/setter automatically
//@Entity, indicating that it is a JPA entity.
@Getter
@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String courseName;

    public Course(String courseName){this.courseName = courseName;}
}

