package com.haison.ProjectManagerment.repository;

import com.haison.ProjectManagerment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // findBy+field entity(username, email,...) we upper case the first letter
    Optional<User> findByUsername(String username); // Find by Username
    Optional<User> existsByEmail(String username); // Check email have exists
    Optional<User> findByUsernameOrEmail(String username, String email); // Check Username or Email
}
