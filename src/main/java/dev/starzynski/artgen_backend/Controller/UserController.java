package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Model.User;
import dev.starzynski.artgen_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/public/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/public/user/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping("/auth/user/{username}")
    public ResponseEntity<?> getUserData(@PathVariable String username) {
        return userService.getUserData(username);
    }

    @PostMapping("/auth/user/process/{username}/{cost}")
    public ResponseEntity<?> processCredits(@PathVariable String username, @PathVariable String cost) {
        return userService.processCredits(username, cost);
    }

    @PostMapping("/auth/user/refund/{username}/{cost}")
    public ResponseEntity<?> refundCredits(@PathVariable String username, @PathVariable String cost) {
        return userService.refundCredits(username, cost);
    }

    @PostMapping("/auth/user/credits/add")
    public ResponseEntity<?> addCredits(@RequestPart String amount, @RequestPart String session_id, @RequestPart String username) {
        return userService.addCredits(amount, session_id, username);
    }
}
