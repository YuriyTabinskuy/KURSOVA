package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user, user.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {  // передаємо весь об'єкт User
        return userService.login(user);  // передаємо об'єкт користувача
    }
}
