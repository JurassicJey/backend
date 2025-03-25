package com.example.spotify.Users.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);  // Example: Find user by username
    Optional<User> findByEmail(String email); // Example: Find user by email
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}