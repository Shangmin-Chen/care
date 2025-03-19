// JournalService.java
package com.example.care.service;

import com.example.care.entity.Journal;
import com.example.care.entity.TreeNode;
import com.example.care.repository.JournalRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class JournalService {
    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public Journal createJournal(TreeNode treeNode, String title, String content) {
        Journal journal = new Journal();
        journal.setTreeNode(treeNode);
        journal.setTitle(title != null ? title : "Untitled Journal");
        journal.setContent(content != null ? content : "");
        treeNode.setJournal(journal); // Set the reverse relationship
        return journalRepository.save(journal);
    }

    public Journal findById(Long id) {
        return journalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Journal not found with id: " + id));
    }

    public Journal updateJournal(Long id, String title, String content) {
        Journal journal = findById(id);
        journal.setTitle(title != null ? title : journal.getTitle());
        journal.setContent(content != null ? content : journal.getContent());
        journal.setUpdatedAt(LocalDateTime.now());
        return journalRepository.save(journal);
    }
}