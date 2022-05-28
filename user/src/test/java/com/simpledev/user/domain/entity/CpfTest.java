package com.simpledev.user.domain.entity;

import com.simpledev.user.exceptions.InvalidCpfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    public void shouldCreateAValidCpf() throws InvalidCpfException {
        var validCpf = "751.273.610-00";

        var cpf = new Cpf(validCpf);

        assertNotNull(cpf);
        assertEquals(validCpf, cpf.getValue());
    }

    @Test
    public void shouldThrowIfInvalidCpfWithEqualDigits() {
        var invalidCpf = "111.111.111-11";

        assertThrows(InvalidCpfException.class, () -> new Cpf(invalidCpf));
    }

    @Test
    public void shouldThrowIfInvalidCpfWithDifferentDigits() {
        var invalidCpf = "123.456.789-99";

        assertThrows(InvalidCpfException.class, () -> new Cpf(invalidCpf));
    }
}