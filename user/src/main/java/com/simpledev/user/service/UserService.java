package com.simpledev.user.service;

import com.simpledev.user.model.UserEntity;

import java.util.List;

public interface UserService {
    public UserEntity save(UserEntity user);

    public UserEntity update(Long id, UserEntity user);

    public List<UserEntity> findAll();

    public UserEntity findById(Long id);

    public UserEntity findByEmail(String email);
}
