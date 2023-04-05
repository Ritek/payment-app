package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.DeleteCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.UpdateCustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/customer")
class CustomerController {
    CustomerService customerService;

    @PostMapping
    ResponseEntity<CustomerDto> createCustomer(@RequestBody NewCustomerDto newCustomerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(newCustomerDto));
    }

    @PostMapping("/admin")
    ResponseEntity<CustomerDto> createAdmin(@RequestBody NewCustomerDto newCustomerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createAdmin(newCustomerDto));
    }

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId).map(
                customerDto -> ResponseEntity.status(HttpStatus.OK).body(customerDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    @GetMapping
    ResponseEntity<Principal> getCustomerBySession(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/all")
    ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @PutMapping
    ResponseEntity<CustomerDto> updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto) {
        return customerService.updateCustomer(updateCustomerDto).map(
                customerDto -> ResponseEntity.status(HttpStatus.OK).body(customerDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    @DeleteMapping("/{customerId}")
    ResponseEntity<DeleteCustomerDto> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
