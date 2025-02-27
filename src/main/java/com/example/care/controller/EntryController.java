package com.example.care.controller;

import com.example.care.entity.Entry;
import com.example.care.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        return new ResponseEntity<>(entryService.createEntry(entry), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Entry>> getUserEntries(@PathVariable String userId) {
        return ResponseEntity.ok(entryService.getUserEntries(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntry(@PathVariable Long id) {
        return ResponseEntity.ok(entryService.getEntry(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable Long id, @RequestBody Entry entry) {
        return ResponseEntity.ok(entryService.updateEntry(id, entry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}