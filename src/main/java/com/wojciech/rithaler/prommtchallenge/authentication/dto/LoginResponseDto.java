package com.wojciech.rithaler.prommtchallenge.authentication.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LoginResponseDto {
    String email;
    String message;
}
