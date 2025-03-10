package com.example.care.controller;

import com.example.care.entity.User;
import com.example.care.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String email, @RequestParam String googleId) {
        User user = userService.createUser(email, googleId);
        return ResponseEntity.ok(user);
    }

    // Add other endpoints as needed (e.g., GET /api/users/{id})
}