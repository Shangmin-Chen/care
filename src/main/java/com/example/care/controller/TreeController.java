// TreeController.java
package com.example.care.controller;

import com.example.care.config.CustomOAuth2User;
import com.example.care.entity.TreeNode;
import com.example.care.entity.User;
import com.example.care.service.TreeService;
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

    @PostMapping("/root")
    public ResponseEntity<TreeNode> createRootNode(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        User user = oauth2User.getApplicationUser();
        TreeNode root = treeService.createRootNode(user);
        return ResponseEntity.ok(root);
    }

    @PostMapping("/child")
    public ResponseEntity<TreeNode> createChildNode(@AuthenticationPrincipal CustomOAuth2User oauth2User, @RequestParam Long parentId) {
        User user = oauth2User.getApplicationUser();
        TreeNode parent = treeService.findById(parentId);
        TreeNode child = treeService.createChildNode(user, parent);
        return ResponseEntity.ok(child);
    }
}