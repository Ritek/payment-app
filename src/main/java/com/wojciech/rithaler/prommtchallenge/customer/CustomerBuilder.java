package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
class CustomerBuilder {
    public Customer create(NewCustomerDto newCustomerDTO) {
        return Customer.builder()
            .role(Role.USER)
            .name(newCustomerDTO.getName())
            .surname(newCustomerDTO.getSurname())
            .email(newCustomerDTO.getEmail())
            .password(newCustomerDTO.getPassword())
            .payments(new ArrayList<>())
            .build();
    }
}
