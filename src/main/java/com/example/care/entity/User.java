package com.example.care.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String googleId; // Optional, for OAuth integration
}