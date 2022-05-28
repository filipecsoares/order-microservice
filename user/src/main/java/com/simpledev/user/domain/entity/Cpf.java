package com.simpledev.user.domain.entity;

import com.simpledev.user.exceptions.InvalidCpfException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class Cpf {
    private static final int FACTOR_DIGIT_1 = 10;
    private static final int  FACTOR_DIGIT_2 = 11;
    private String value;

    public Cpf(String value) throws InvalidCpfException {
        if(!validate(value)) {
            throw new InvalidCpfException();
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    private boolean validate (String cpf) {
        if (cpf == null) return false;
        cpf = this.cleanCpf(cpf);
        if (!this.isValidLength(cpf)) return false;
        if (this.hasAllDigitsEqual(cpf)) return false;
		final var digit1 = this.calculateCheckDigit(cpf, this.FACTOR_DIGIT_1);
		final var digit2 = this.calculateCheckDigit(cpf, this.FACTOR_DIGIT_2);
        var checkDigit = this.extractCheckDigit(cpf);
		final var calculatedDigit = digit1 + digit2;
        return Integer.parseInt(checkDigit) == calculatedDigit;
    }

    private String cleanCpf (final String cpf) {
        return cpf.replaceAll("[.-]", "");
    }

    private boolean isValidLength (final String cpf) {
        return cpf.length() == 11;
    }

    private boolean hasAllDigitsEqual (final String cpf) {
        var firstDigit = cpf.substring(0, 1);
        return Arrays.stream(cpf.split("")).filter(digit -> digit.equals(firstDigit)).count() == cpf.length();
    }

    private int calculateCheckDigit (final String cpf, int factor) {
        var total = 0;
        for (String digit : cpf.split("")) {
            if (factor > 1) total += Integer.parseInt(digit) * factor--;
        }
		final int rest = total%11;
        return (rest < 2) ? 0 : (11 - rest);
    }

    private String extractCheckDigit (final String cpf) {
        return cpf.substring(cpf.length() - 3);
    }
}
