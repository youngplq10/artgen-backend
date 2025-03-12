package dev.starzynski.artgen_backend.Service;

import dev.starzynski.artgen_backend.Model.Art;
import dev.starzynski.artgen_backend.Model.Payment;
import dev.starzynski.artgen_backend.Model.User;
import dev.starzynski.artgen_backend.Repository.PaymentRepository;
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
import java.util.Comparator;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseEntity<?> registerUser(User user) {
        try {
            Boolean isExistingByUsername = userRepository.findByUsername(user.getUsername()).isPresent();
            Boolean isExistingByEmail = userRepository.findByEmail(user.getEmail()).isPresent();

            if (isExistingByUsername) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User with this username already exists."));
            }

            if (isExistingByEmail) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User with this e-mail already exists."));
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
                    .body(Collections.singletonMap("message", "Failed to log in. Please try again"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Server error in login service."));
    }

    public ResponseEntity<?> getUserData(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                user.get().getArts().sort(Comparator.comparing(Art::getId).reversed());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(user.get());
            } else {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User not found."));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getUsersCredits(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(user.get().getCredits());
            } else {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User not found."));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> processCredits(String username, String cost) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                if (user.get().getCredits() >= Integer.parseInt(cost)) {
                    user.get().setCredits(user.get().getCredits() - Integer.parseInt(cost));
                    userRepository.save(user.get());

                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(true);
                } else {
                    return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body(Collections.singletonMap("message", "Not enough credits."));
                }
            } else {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User not found."));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> refundCredits(String username, String cost) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                user.get().setCredits(user.get().getCredits() + Integer.parseInt(cost));
                userRepository.save(user.get());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(user.get().getCredits());
            } else {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User not found."));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> addCredits(String amount, String sessionId, String username) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            Optional<Payment> optionalPayment = paymentRepository.findBySessionId(sessionId);

            if (optionalUser.isPresent() && optionalPayment.isEmpty()) {
                User user = optionalUser.get();
                Payment payment = new Payment();
                payment.setAmount(Integer.parseInt(amount));
                payment.setSessionId(sessionId);
                payment.setUser(user);

                user.setCredits(user.getCredits() + Integer.parseInt(amount));
                userRepository.save(user);
                paymentRepository.insert(payment);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(true);
            } else {
                System.out.println("return false");
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
