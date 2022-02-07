package com.example.assessmentstudentmanagement.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


    @Getter
    @Entity
    @Setter
    public class Course {

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
        private Long courseId;
        private String courseName;


        public Course() {}

        public Course(String courseName){this.courseName = courseName;}
    }

