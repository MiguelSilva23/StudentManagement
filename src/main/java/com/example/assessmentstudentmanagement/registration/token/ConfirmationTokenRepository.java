package com.example.assessmentstudentmanagement.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken,Long> {
  //@Query is Optional as most fo finding methods
  Optional<ConfirmationToken> findByToken(String token);


  //@Transactional - for the JPA module we have this annotation on the implementation class backing the proxy (SimpleJpaRepository).
  // first, persisting and deleting objects requires a transaction in JPA.
  // second we need to make sure a transaction is running, which we do by having the method annotated with @Transactional.

  //The @Modifying annotation is used to enhance the @Query annotation
  // so that we can execute not only SELECT queries, but also UPDATE, SET, DELETE, and even DDL queries.

  @Transactional
  @Modifying
  @Query("UPDATE ConfirmationToken c "+
          "SET c.confirmedAt = ?2 "+
          "WHERE c.token = ?1")
  int updateConfirmedAt(String token,
                        LocalDateTime confirmedAt);
}