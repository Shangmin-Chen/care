package com.example.care.service;

import com.example.care.entity.Entry;
import com.example.care.repository.EntryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public List<Entry> getUserEntries(String userId) {
        return entryRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Entry getEntry(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public Entry updateEntry(Long id, Entry entryDetails) {
        Entry entry = getEntry(id);
        entry.setTitle(entryDetails.getTitle());
        entry.setContent(entryDetails.getContent());
        entry.setTags(entryDetails.getTags());
        entry.setUpdatedAt(LocalDateTime.now());
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }
}