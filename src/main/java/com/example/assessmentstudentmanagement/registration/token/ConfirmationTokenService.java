package com.example.assessmentstudentmanagement.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//Service -->  annotates classes at the service layer.
// These class files are used to write business logic in a different layer, separated from @RestController class file.

//AllArgsConstruct. -->  generates a constructor with 1 parameter for each field in your class.

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;
  public void saveConfirmationToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationToken> getToken(String token){
    return confirmationTokenRepository.findByToken(token);
  }

  public int setConfirmedAt(String token){
    return confirmationTokenRepository.updateConfirmedAt(
            token, LocalDateTime.now()
    );
  }


}
