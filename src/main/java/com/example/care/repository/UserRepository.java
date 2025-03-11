// UserRepository.java
package com.example.care.repository;

import com.example.care.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}