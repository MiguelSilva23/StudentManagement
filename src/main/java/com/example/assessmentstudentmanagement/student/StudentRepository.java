package com.example.assessmentstudentmanagement.student;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student, Long> {


//in the JpaRep. specifying entity for the Jpa and primary key type entities student and primary key type is id
//let´s add by find by email and password

    Optional<Student> findByEmail(String email);
    Optional<Student> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Student s "+
                    "SET s.enabled = TRUE "+
                    "WHERE s.email = ?1"
    )
    int enableStudent(String email);


}