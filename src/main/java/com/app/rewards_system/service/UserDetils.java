package com.app.rewards_system.service;

import com.app.rewards_system.model.Customer;
import com.app.rewards_system.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetils implements UserDetails {

    private String customerId;
    private Role roles;
    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
        //return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet());

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return customerId;
    }
    public static UserDetils build(Customer customer) {
        return UserDetils.builder()
                .customerId(customer.getCustomerId())
                .roles(customer.getRole())
                .build();

    }
}
