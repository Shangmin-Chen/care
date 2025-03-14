package com.example.care.repository;

import com.example.care.entity.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreeNodeRepository extends JpaRepository<TreeNode, Long> {
    List<TreeNode> findByTreeId(Long treeId); // To fetch all nodes of a tree
    List<TreeNode> findByUserEmailAndParentIsNull(String userEmail); // To fetch root nodes per user
}