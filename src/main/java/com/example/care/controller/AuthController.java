// AuthController.java
package com.example.care.controller;

import com.example.care.config.CustomOAuth2User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/success")
    public ResponseEntity<String> loginSuccess(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        return ResponseEntity.ok("Login successful for user: " + oauth2User.getName());
    }

    @GetMapping("/logout-success")
    public ResponseEntity<String> logoutSuccess() {
        return ResponseEntity.ok("Logout successful");
    }
}