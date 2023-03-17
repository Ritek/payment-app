package com.wojciech.rithaler.prommtchallenge.Controller;

import lombok.Getter;

@Getter
public class Violation {

    private final String fieldName;

    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
