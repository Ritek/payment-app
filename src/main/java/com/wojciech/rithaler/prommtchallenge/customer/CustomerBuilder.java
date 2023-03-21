package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.DTO.NewCustomerDTO;

class CustomerBuilder {
    public Customer create(NewCustomerDTO newCustomerDTO) {
        return Customer.builder()
            .role(Role.USER)
            .name(newCustomerDTO.getName())
            .surname(newCustomerDTO.getSurname())
            .email(newCustomerDTO.getEmail())
            .password(newCustomerDTO.getPassword())
            .build();
    }
}
