package com.wojciech.rithaler.prommtchallenge.payment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyConstraint {
    String message() default "Not supported currency!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
