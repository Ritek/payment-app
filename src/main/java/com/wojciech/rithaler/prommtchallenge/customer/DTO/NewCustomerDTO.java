package com.wojciech.rithaler.prommtchallenge.customer.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NewCustomerDTO {
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
