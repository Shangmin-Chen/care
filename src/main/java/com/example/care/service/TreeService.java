// TreeService.java
package com.example.care.service;

import com.example.care.entity.Tree;
import com.example.care.entity.TreeNode;
import com.example.care.entity.User;
import com.example.care.repository.TreeNodeRepository;
import com.example.care.repository.TreeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TreeService {
    private final TreeNodeRepository treeNodeRepository;
    private final TreeRepository treeRepository;
    private final JournalService journalService; // Add this

    public TreeService(TreeNodeRepository treeNodeRepository, TreeRepository treeRepository, JournalService journalService) {
        this.treeNodeRepository = treeNodeRepository;
        this.treeRepository = treeRepository;
        this.journalService = journalService; // Add this
    }

    @Transactional
    public TreeNode createRootNode(User user, String name) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Create or fetch a Tree for this user
        Tree tree = new Tree();
        tree.setUser(user);
        tree.setName(name != null ? name : "Unnamed Tree");
        tree = treeRepository.save(tree);

        TreeNode root = new TreeNode();
        root.setUser(user);
        root.setTree(tree);
        root.setName(name);
        root.setParent(null);
        root = treeNodeRepository.save(root);

        // Create an empty journal for this node
        journalService.createJournal(root, "Untitled Journal", ""); // Default title and empty content

        return root;
    }

    @Transactional
    public TreeNode createChildNode(User user, TreeNode parent, String name) {
        if (user == null || parent == null) {
            throw new IllegalArgumentException("User and parent cannot be null");
        }
        if (!parent.getUser().getEmail().equals(user.getEmail())) {
            throw new SecurityException("User does not own the parent node");
        }
        TreeNode child = new TreeNode();
        child.setUser(user);
        child.setTree(parent.getTree());
        child.setParent(parent);
        child.setName(name);
        child = treeNodeRepository.save(child);

        // Create an empty journal for this node
        journalService.createJournal(child, "Untitled Journal", ""); // Default title and empty content

        return child;
    }

    public TreeNode findById(Long id) {
        return treeNodeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TreeNode not found with id: " + id));
    }
}