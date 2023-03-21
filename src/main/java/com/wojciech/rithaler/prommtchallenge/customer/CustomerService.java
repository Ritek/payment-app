package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.DeleteCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.UpdateCustomerDto;

import java.util.List;
import java.util.Optional;

interface CustomerService {
    CustomerDto createCustomer(NewCustomerDto newCustomerDto);
    Optional<CustomerDto> findCustomerById(Long customerId);
    List<CustomerDto> getAllCustomers();
    Optional<CustomerDto> updateCustomer(UpdateCustomerDto updateCustomerDto);
    Optional<DeleteCustomerDto> deleteCustomerById(Long customerId);
}
