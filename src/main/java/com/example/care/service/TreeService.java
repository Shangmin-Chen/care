// TreeService.java
package com.example.care.service;

import com.example.care.entity.TreeNode;
import com.example.care.entity.User;
import com.example.care.repository.TreeNodeRepository;
import org.springframework.stereotype.Service;

@Service
public class TreeService {
    private final TreeNodeRepository treeNodeRepository;

    public TreeService(TreeNodeRepository treeNodeRepository) {
        this.treeNodeRepository = treeNodeRepository;
    }

    public TreeNode createRootNode(User user) {
        TreeNode root = new TreeNode();
        root.setUser(user);
        root.setParent(null);
        return treeNodeRepository.save(root);
    }

    public TreeNode createChildNode(User user, TreeNode parent) {
        TreeNode child = new TreeNode();
        child.setUser(user);
        child.setParent(parent);
        return treeNodeRepository.save(child);
    }

    public TreeNode findById(Long id) {
        return treeNodeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TreeNode not found with id: " + id));
    }
}