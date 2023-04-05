package com.wojciech.rithaler.prommtchallenge.authentication.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginDto {
    @NonNull
    @NotEmpty
    String email;

    @NonNull
    @NotEmpty
    String password;
}
