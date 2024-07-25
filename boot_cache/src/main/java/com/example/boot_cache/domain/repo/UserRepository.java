package com.example.boot_cache.domain.repo;

import com.example.boot_cache.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
