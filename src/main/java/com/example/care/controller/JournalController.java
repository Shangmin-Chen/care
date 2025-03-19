// JournalController.java
package com.example.care.controller;

import com.example.care.entity.Journal;
import com.example.care.entity.TreeNode;
import com.example.care.service.JournalService;
import com.example.care.service.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journals")
public class JournalController {
    private final JournalService journalService;
    private final TreeService treeService; // Add this to fetch TreeNode

    public JournalController(JournalService journalService, TreeService treeService) {
        this.journalService = journalService;
        this.treeService = treeService;
    }

    @GetMapping("/node/{treeNodeId}")
    public ResponseEntity<Journal> getJournalByTreeNodeId(@PathVariable Long treeNodeId) {
        TreeNode treeNode = treeService.findById(treeNodeId);
        Journal journal = treeNode.getJournal();
        if (journal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(journal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(
            @PathVariable Long id,
            @RequestBody UpdateJournalRequest request) {
        Journal updatedJournal = journalService.updateJournal(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(updatedJournal);
    }

    // Inner class for update request
    static class UpdateJournalRequest {
        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}