package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
class CustomerDtoCreator {
    public CustomerDto createDto(Customer customer) {
        return new CustomerDto(
            customer.getID(),
            customer.getName(),
            customer.getSurname(),
            customer.getEmail()
        );
    }
}
