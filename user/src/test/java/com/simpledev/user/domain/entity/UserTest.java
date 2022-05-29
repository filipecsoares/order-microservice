package com.simpledev.user.domain.entity;

import com.simpledev.user.exceptions.InvalidCpfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void shouldCreateAValidUser() throws InvalidCpfException {
        var cpf = "751.273.610-00";
        var email = "test@test.com";
        var name = "test";
        var password = "password";

        var user = new User(name, email, password, cpf);

        assertNotNull(user);
    }

    @Test
    public void shouldNotCreateAnUserWithInvalidCpf() {
        var invalidCpf = "111.111.111-11";

        assertThrows(InvalidCpfException.class, () -> new User("Name", "email@email.com", "password", invalidCpf));
    }

    @Test
    public void shouldNotCreateAnUserWithInvalidEmail() {
        var cpf = "751.273.610-00";
        var invalidEmail = "";
        var name = "test";
        var password = "password";

        assertThrows(IllegalArgumentException.class, () -> new User(name, invalidEmail, password, cpf));
    }
}