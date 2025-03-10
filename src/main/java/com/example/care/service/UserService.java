package com.example.care.service;

import com.example.care.entity.User;
import com.example.care.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String googleId) {
        User user = new User();
        user.setEmail(email);
        user.setGoogleId(googleId);
        return userRepository.save(user);
    }

    // Add other methods as needed (e.g., findUserById)
}