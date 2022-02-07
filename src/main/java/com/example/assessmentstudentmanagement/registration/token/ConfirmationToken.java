package com.example.assessmentstudentmanagement.registration.token;



import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.assessmentstudentmanagement.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//NoArgsConstructor  --> will generate a constructor with no parameters. If this is not possible (because of final fields),
// a compiler error will result instead, unless @NoArgsConstructor(force = true) is used, then all final fields are initialized with 0 / false / null.

// @Entity, indicating that it is a JPA entity (mandatory) When you create a new entity you have to do at least two things
//annotated it with @Entity  AND create an id field and annotate it with @Id
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
  @Id
  @SequenceGenerator(
          name = "confirmation_token_sequence",
          sequenceName = "confirmation_token_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "confirmation_token_sequence"
  )

  private Long id;

  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Column(nullable = false)
  private LocalDateTime expiresAt;
  //confirmation is optional
  private LocalDateTime confirmedAt;
  @ManyToOne
  @JoinColumn(nullable = false,
          name = "student_id"
  )
  private Student student;

  public ConfirmationToken(String token,
                           LocalDateTime createdAt,
                           LocalDateTime expiresAt,
                           Student student) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.student = student;
  }
}
