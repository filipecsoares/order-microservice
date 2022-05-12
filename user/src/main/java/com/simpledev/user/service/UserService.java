package com.simpledev.user.service;

import com.simpledev.user.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);
    public List<User> findAll();
}
