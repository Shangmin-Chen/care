package com.example.care.repository;

import com.example.care.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    // Add custom query methods here if needed
}