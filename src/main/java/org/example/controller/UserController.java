package org.example.controller;

import org.example.model.User; // Виправлено
import org.example.service.UserService; // Виправлено
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.authenticateUser(email, password)
                .map(user -> ResponseEntity.ok("Login successful"))
                .orElse(ResponseEntity.status(401).body("Invalid email or password"));
    }
}