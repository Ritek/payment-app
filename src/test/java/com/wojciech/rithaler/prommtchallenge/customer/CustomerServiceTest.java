package com.wojciech.rithaler.prommtchallenge.customer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    CustomerBuilder customerBuilder;
    CustomerDtoCreator customerDtoCreator;
    CustomerService customerService;

    @BeforeEach
    void init() {
        customerBuilder = new CustomerBuilder();
        customerDtoCreator = new CustomerDtoCreator();
        customerService = new CustomerServiceImpl(
                customerRepository, customerBuilder, customerDtoCreator
        );
    }

}
