// TreeNode.java
package com.example.care.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tree_nodes")
@Data
public class TreeNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TreeNode parent;

    @OneToOne(mappedBy = "treeNode", cascade = CascadeType.ALL)
    @JsonManagedReference // This side is serialized
    private Journal journal;
}