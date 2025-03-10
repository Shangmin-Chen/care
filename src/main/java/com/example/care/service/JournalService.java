package com.example.care.service;

import com.example.care.entity.Journal;
import com.example.care.entity.TreeNode;
import com.example.care.repository.JournalRepository;
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
        journal.setTitle(title);
        journal.setContent(content);
        return journalRepository.save(journal);
    }

    // Add other methods as needed (e.g., findJournalById)
}