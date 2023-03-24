package com.wojciech.rithaler.prommtchallenge.customer.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CustomerDto {
    private Long ID;

    private String name;

    private String surname;

    private String email;
}
