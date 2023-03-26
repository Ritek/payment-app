package com.wojciech.rithaler.prommtchallenge.customer;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
public class CustomerPrincipal implements UserDetails {
    private Customer customer;
    private Long ID;

    public CustomerPrincipal(Customer customer) {
        super();
        this.customer = customer;
        this.ID = customer.getID();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return List.of(new SimpleGrantedAuthority(customer.getRole().toString()));
        return Collections.singleton(new SimpleGrantedAuthority(customer.getRole().toString()));
    }

    public Long getId() {
        return customer.getID();
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
