package com.example.care.repository;

import com.example.care.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByUserIdOrderByCreatedAtDesc(String userId);
}