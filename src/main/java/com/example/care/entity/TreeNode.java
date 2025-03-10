package com.example.care.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tree_nodes")
@Data
public class TreeNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user; // Foreign key to User

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TreeNode parent; // Self-referential foreign key for hierarchy

    @OneToOne(mappedBy = "treeNode", cascade = CascadeType.ALL)
    private Journal journal; // One-to-one relationship with Journal
}