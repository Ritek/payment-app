package com.wojciech.rithaler.prommtchallenge.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UpdateCustomerDto {
    @NonNull
    Long ID;
    String name;
    String surname;
    String email;
    String password;
}
