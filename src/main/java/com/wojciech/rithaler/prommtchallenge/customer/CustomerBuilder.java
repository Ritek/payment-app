package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
class CustomerBuilder {
    private final PasswordEncoder passwordEncoder;
    public Customer create(NewCustomerDto newCustomerDTO) {
        return Customer.builder()
            .role(Role.USER)
            .name(newCustomerDTO.getName())
            .surname(newCustomerDTO.getSurname())
            .email(newCustomerDTO.getEmail())
            .password(passwordEncoder.encode(newCustomerDTO.getPassword()))
            .build();
    }

    public Customer createAdmin(NewCustomerDto newCustomerDTO) {
        return Customer.builder()
                .role(Role.ADMIN)
                .name(newCustomerDTO.getName())
                .surname(newCustomerDTO.getSurname())
                .email(newCustomerDTO.getEmail())
                .password(passwordEncoder.encode(newCustomerDTO.getPassword()))
                .build();
    }
}
