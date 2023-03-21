package com.wojciech.rithaler.prommtchallenge.customer;

import java.util.List;

interface CustomerService {
    Customer createCustomer();
    Customer findCustomerById();
    List<Customer> getAllCustomers();
    Customer updateCustomerById();
    Long deleteCustomerById();
}
