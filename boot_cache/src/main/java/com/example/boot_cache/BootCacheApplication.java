package com.example.boot_cache;

import com.example.boot_cache.domain.repo.UserRepository;
import com.example.boot_cache.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BootCacheApplication implements ApplicationRunner {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BootCacheApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.save(User.builder().name("jack1").email("jack1@naver.com").build());
		userRepository.save(User.builder().name("jack2").email("jack2@naver.com").build());
		userRepository.save(User.builder().name("jack3").email("jack3@naver.com").build());
		userRepository.save(User.builder().name("jack4").email("jack4@naver.com").build());

	}
}
