package dev.starzynski.artgen_backend.Service;

import dev.starzynski.artgen_backend.Model.User;
import dev.starzynski.artgen_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public ResponseEntity<?> registerUser(User user) {
        try {
            Boolean isExisting = userRepository.findByUsername(user.getUsername()).isPresent();

            if (isExisting) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User already exists."));
            }

            userRepository.insert(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("jwt", jwtService.generateToken(user.getUsername())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> loginUser(User user) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if(authentication.isAuthenticated()){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(Collections.singletonMap("jwt", jwtService.generateToken(user.getUsername())));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", "User doesn't exist."));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Server error in login service."));
    }
}
