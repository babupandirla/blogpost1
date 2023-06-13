package com.wrapy.blogpost.repositories;

import com.wrapy.blogpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom query methods can be defined here
}
