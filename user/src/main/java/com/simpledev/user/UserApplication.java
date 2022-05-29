package com.simpledev.user;

import com.simpledev.user.model.UserEntity;
import com.simpledev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class UserApplication {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @PostConstruct
    public void populateUser() {
        var users = Arrays.asList(
                UserEntity.builder().email("test1@test.com").name("Test 1").password("1234").build(),
                UserEntity.builder().email("test2@test.com").name("Test 2").password("1234").build());
        users.forEach(userRepository::save);
    }
}
