package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
}
