package com.example.care.controller;

import com.example.care.entity.Journal;
import com.example.care.entity.TreeNode;
import com.example.care.service.JournalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journals")
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestParam Long treeNodeId, @RequestParam String title, @RequestParam String content) {
        // Placeholder: Replace with actual treeNode retrieval logic
        TreeNode treeNode = new TreeNode();
        treeNode.setId(treeNodeId);
        Journal journal = journalService.createJournal(treeNode, title, content);
        return ResponseEntity.ok(journal);
    }

    // Add other endpoints as needed (e.g., GET /api/journals/{id})
}