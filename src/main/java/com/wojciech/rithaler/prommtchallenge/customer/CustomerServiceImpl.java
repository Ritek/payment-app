package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.DeleteCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.UpdateCustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerBuilder customerBuilder;
    private CustomerDtoCreator customerDtoCreator;

    @Override
    public CustomerDto createCustomer(NewCustomerDto newCustomerDto) {
        Customer customer = customerRepository.save(customerBuilder.create(newCustomerDto));
        return customerDtoCreator.createDto(customer);
    }

    @Override
    public CustomerDto createAdmin(NewCustomerDto newCustomerDto) {
        Customer customer = customerRepository.save(customerBuilder.createAdmin(newCustomerDto));
        return customerDtoCreator.createDto(customer);
    }

    @Override
    public Optional<CustomerDto> findCustomerById(Long customerId) {
        System.out.println(customerRepository.findById(customerId));
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
        Optional.ofNullable(updateCustomerDto.getName()).ifPresent(customer::setName);
        Optional.ofNullable(updateCustomerDto.getSurname()).ifPresent(customer::setSurname);

        return customer;
    }
}
