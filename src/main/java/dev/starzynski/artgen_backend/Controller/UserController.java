package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Model.User;
import dev.starzynski.artgen_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
