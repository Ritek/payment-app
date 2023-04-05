package com.wojciech.rithaler.prommtchallenge.customer;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
/*
public class WithMockCustomUserUserSecurityContextFactory implements WithSecurityContextFactory<CustomerServiceIT.WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(CustomerServiceIT.WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        String password = "{noop}password";

        Customer principal = Customer.builder()
                .ID(1L)
                .role(Role.USER)
                .email("email@email.com")
                .password(password)
                .name("name")
                .surname("surname")
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(principal.getEmail(), principal.getPassword());
        context.setAuthentication(auth);
        return context;
    }
}*/
