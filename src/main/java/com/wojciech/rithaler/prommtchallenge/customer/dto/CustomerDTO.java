package com.wojciech.rithaler.prommtchallenge.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CustomerDTO {
    @NonNull
    Long ID;
    @NonNull
    @NotEmpty
    String name;
    @NonNull
    @NotEmpty
    String surname;
    @NonNull
    @NotEmpty
    String email;
}
