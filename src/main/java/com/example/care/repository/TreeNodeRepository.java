package com.example.care.repository;

import com.example.care.entity.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeNodeRepository extends JpaRepository<TreeNode, Long> {
    // Add custom query methods here if needed
}