package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
class CustomerDtoCreator {
    public CustomerDTO createDto(Customer customer) {
        return new CustomerDTO(
            customer.getID(),
            customer.getName(),
            customer.getSurname(),
            customer.getEmail()
        );
    }
}
