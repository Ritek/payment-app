package com.wojciech.rithaler.prommtchallenge.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginDto {
    String email;
    String password;
}
