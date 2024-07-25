package com.example.boot_cache.domain.repository;

import com.example.boot_cache.domain.entity.RedisHashUser;
import org.springframework.data.repository.CrudRepository;

public interface RedisHashUserRepository extends CrudRepository<RedisHashUser, Long> {
}
