package com.wojciech.rithaler.prommtchallenge.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NewCustomerDto {
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
