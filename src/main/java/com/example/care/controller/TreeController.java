// TreeController.java
package com.example.care.controller;

import com.example.care.config.CustomOAuth2User;
import com.example.care.entity.TreeNode;
import com.example.care.entity.User;
import com.example.care.service.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tree")
public class TreeController {
    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/create-root")
    public ResponseEntity<TreeNode> createRootNode(
            @AuthenticationPrincipal CustomOAuth2User oauth2User,
            @RequestBody(required = false) CreateRootNodeRequest request) {
        User user = oauth2User.getApplicationUser();
        String name = request != null ? request.getName() : null;
        TreeNode root = treeService.createRootNode(user, name);
        return ResponseEntity.ok(root);
    }

    @PostMapping("/create-child")
    public ResponseEntity<TreeNode> createChildNode(
            @AuthenticationPrincipal CustomOAuth2User oauth2User,
            @RequestParam Long parentId,
            @RequestBody(required = false) CreateChildNodeRequest request) {
        User user = oauth2User.getApplicationUser();
        TreeNode parent = treeService.findById(parentId);
        try {
            System.out.println("Authenticated User Email: " + user.getEmail());
            System.out.println("Parent Node User Email: " + parent.getUser().getEmail());
            String name = request != null ? request.getName() : null;
            TreeNode child = treeService.createChildNode(user, parent, name);
            return ResponseEntity.ok(child);
        } catch (SecurityException e) {
            System.out.println("SecurityException: User does not own the parent node");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    // Add the missing inner class here
    static class CreateRootNodeRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // Add the missing inner class here
    static class CreateChildNodeRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}