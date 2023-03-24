package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
