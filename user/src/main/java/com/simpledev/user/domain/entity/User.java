package com.simpledev.user.domain.entity;

import com.simpledev.user.exceptions.InvalidCpfException;

public class User {
    private String name;
    private String email;
    private String password;
    private Cpf cpf;

    public User(String name, String email, String password, String cpf) throws InvalidCpfException {
        this.cpf = new Cpf(cpf);
        if(password == null || password.length() < 4) throw new IllegalArgumentException("Password must have four characters or more.");
        this.password = password;
        this.name = name;
        if(email == null || email.isBlank()) throw new IllegalArgumentException("Email is required.");
        this.email = email;
    }
}
