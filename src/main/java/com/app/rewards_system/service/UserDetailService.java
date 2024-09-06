package com.app.rewards_system.service;

import com.app.rewards_system.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final CustomerService customerService;


    public UserDetailService(CustomerService customerService) {
        this.customerService = customerService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer= customerService.getCustomer(username);
        return UserDetils.build(customer);
    }
}
