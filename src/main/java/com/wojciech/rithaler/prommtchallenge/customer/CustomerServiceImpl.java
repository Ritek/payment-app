package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.DeleteCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.UpdateCustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerBuilder customerBuilder;
    private CustomerDtoCreator customerDtoCreator;

    @Override
    public CustomerDto createCustomer(NewCustomerDto newCustomerDto) {
        Customer customer = customerRepository.save(customerBuilder.create(newCustomerDto));
        return customerDtoCreator.createDto(customer);
    }

    @Override
    public Optional<CustomerDto> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerDtoCreator::createDto);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerDtoCreator::createDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDto> updateCustomer(UpdateCustomerDto updateCustomerDto) {
        return customerRepository.findById(updateCustomerDto.getID())
                .map(customer -> {
                    Customer updatedCustomer = updateCustomer(customer, updateCustomerDto);
                    Customer result = customerRepository.save(updatedCustomer);
                    return customerDtoCreator.createDto(result);
                });
    }

    @Override
    public Optional<DeleteCustomerDto> deleteCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return new DeleteCustomerDto(customer.getID());
                });
    }

    private Customer updateCustomer(Customer customer, UpdateCustomerDto updateCustomerDto) {
        return new Customer(
                customer.getID(),
                customer.getRole(),
                Optional.of(updateCustomerDto.getName()).orElse(customer.getName()),
                Optional.of(updateCustomerDto.getSurname()).orElse(customer.getSurname()),
                Optional.of(updateCustomerDto.getEmail()).orElse(customer.getEmail()),
                Optional.of(updateCustomerDto.getPassword()).orElse(customer.getPassword())
        );
    }
}
