package com.LeMart.repo;

import com.LeMart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Find user by email
    Optional<User> findByUsername(String username); // Find user by username
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}