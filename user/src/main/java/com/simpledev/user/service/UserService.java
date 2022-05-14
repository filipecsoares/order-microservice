package com.simpledev.user.service;

import com.simpledev.user.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);

    public User update(Long id, User user);

    public List<User> findAll();

    public User findById(Long id);

    public User findByEmail(String email);
}
