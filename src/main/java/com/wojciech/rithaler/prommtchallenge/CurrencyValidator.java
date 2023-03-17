package com.wojciech.rithaler.prommtchallenge;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {
    @Override
    public void initialize(CurrencyConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String currencyField, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> supportedCurrencies = new HashSet<>(Arrays.asList("USD", "EUR"));
        return supportedCurrencies.contains(currencyField);
    }
}
