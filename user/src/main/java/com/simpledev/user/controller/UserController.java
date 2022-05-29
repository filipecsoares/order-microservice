package com.simpledev.user.controller;

import com.simpledev.user.codec.Codec;
import com.simpledev.user.model.UserEntity;
import com.simpledev.user.protocols.UserResponse;
import com.simpledev.user.protocols.UsersResponse;
import com.simpledev.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserEntity user) {
        return new ResponseEntity<>(Codec.toResponse(userService.save(user)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserEntity user) {
        return new ResponseEntity<>(Codec.toResponse(userService.update(id, user)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UsersResponse> findAll() {
        return new ResponseEntity<>(Codec.toResponse(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {
        return new ResponseEntity<>(Codec.toResponse(userService.findByEmail(email)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable Long id) {
        return new ResponseEntity<>(Codec.toResponse(userService.findById(id)), HttpStatus.OK);
    }
}
