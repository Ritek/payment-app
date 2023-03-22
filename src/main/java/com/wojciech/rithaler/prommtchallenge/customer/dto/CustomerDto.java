package com.wojciech.rithaler.prommtchallenge.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerDto {
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
