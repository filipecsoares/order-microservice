package com.simpledev.user.controller;

import com.simpledev.user.codec.Codec;
import com.simpledev.user.model.User;
import com.simpledev.user.protocols.UserResponse;
import com.simpledev.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody User user) {
        return new ResponseEntity<>(Codec.toResponse(userService.save(user)), HttpStatus.CREATED);
    }
}
