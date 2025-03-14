// Tree.java (New Entity)
package com.example.care.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trees")
@Data
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    @Column(nullable = true)
    private String name; // Optional name for the tree
}