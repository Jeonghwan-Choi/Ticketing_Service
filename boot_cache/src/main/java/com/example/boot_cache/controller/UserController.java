package com.example.boot_cache.controller;

import com.example.boot_cache.domain.entity.RedisHashUser;
import com.example.boot_cache.domain.entity.User;
import com.example.boot_cache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser3(id);
    }

    @GetMapping("redishash-users/{id}")
    public RedisHashUser getUser2(@PathVariable Long id) {
        return userService.getUser2(id);
    }

}
