package com.wojciech.rithaler.prommtchallenge.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojciech.rithaler.prommtchallenge.customer.dto.CustomerDto;
import com.wojciech.rithaler.prommtchallenge.customer.dto.NewCustomerDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceIT {
    static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    static final String BASE_URL = "http://127.0.0.1:8080/api/customer";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    void postEndpointShouldCreatePayment() throws Exception {
        NewCustomerDto newCustomerDto = new NewCustomerDto(
                "name", "surname", "email@email.com", "password"
        );
        String newCustomerJson = objectMapper.writeValueAsString(newCustomerDto);

        CustomerDto customerDto = new CustomerDto(1L, "name", "surname", "email@email.com");
        String customerJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(post(BASE_URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(newCustomerJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(customerJson));
    }

    @Test
    @Order(2)
    void getEndpointShouldFindPaymentById() throws Exception {
        String url = BASE_URL + "/1";

        CustomerDto customerDto = new CustomerDto(1L, "name", "surname", "email@email.com");
        String customerJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));
    }

}
