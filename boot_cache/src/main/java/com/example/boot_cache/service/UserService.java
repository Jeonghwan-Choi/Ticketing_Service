package com.example.boot_cache.service;


import com.example.boot_cache.domain.entity.User;
import com.example.boot_cache.domain.entity.RedisHashUser;
import com.example.boot_cache.domain.repo.UserRepository;
import com.example.boot_cache.domain.repository.RedisHashUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.example.boot_cache.config.CacheConfig.CACHE1;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> userRedisTemplate;
    private final RedisTemplate<String, Object> objectRedisTemplate;
    private final RedisHashUserRepository redisHashUserRepository;

//    public User getUser(final Long id) {
//        var key = "users:%d".formatted(id);
//        // 1. cache get
//        var cachedUser = userRedisTemplate.opsForValue().get(key);
//        if(cachedUser != null) {
//            return cachedUser;
//        }
//
//        User user = userRepository.findById(id).orElseThrow();
//        userRedisTemplate.opsForValue().set(key, user);
//
//        // 2. else bf - > cache set
//        return user;
//    }

    public User getUser(final Long id) {
        var key = "users:%d".formatted(id);
        // 1. cache get
        var cachedUser = objectRedisTemplate.opsForValue().get(key);
        if (cachedUser != null) {
            return (User) cachedUser;
        }

        User user = userRepository.findById(id).orElseThrow();
        // 2. else bf - > cache set
        objectRedisTemplate.opsForValue().set(key, user);

        return user;
    }

    public RedisHashUser getUser2(final Long id) {
        var cacheUser = redisHashUserRepository.findById(id).orElseGet(() -> {
            User user = userRepository.findById(id).orElseThrow();
            return redisHashUserRepository.save(
                    RedisHashUser.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .createdAt(user.getCreatedAt())
                            .updatedAt(user.getUpdatedAt())
                            .build()
            );
        });
        return cacheUser;
    }

    @Cacheable(cacheNames = CACHE1, key = "'user:' +  #id")
    public User getUser3(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
