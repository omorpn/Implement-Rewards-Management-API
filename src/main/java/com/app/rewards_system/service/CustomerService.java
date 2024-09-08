package com.app.rewards_system.service;

import com.app.rewards_system.dto.CustomerDto;
import com.app.rewards_system.dto.RewardHistoryDto;
import com.app.rewards_system.dto.TokenRequestDto;
import com.app.rewards_system.dto.TokenResponseDto;
import com.app.rewards_system.jwt.JwtToken;
import com.app.rewards_system.model.CashbackHistory;
import com.app.rewards_system.model.Customer;
import com.app.rewards_system.repository.CustomerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JwtToken jwtToken;

    public CustomerService(CustomerRepository customerRepository, JwtToken jwtToken) {
        this.customerRepository = customerRepository;

        this.jwtToken = jwtToken;
    }

    //Get customer by id
    public Customer getCustomer(String id) {
        //fetch the customer or throw exception not found
        return customerRepository.findByCustomerId(id).orElseThrow(()-> new IllegalArgumentException("Customer not found"));
    }

    //Get All Reward History
    public List<RewardHistoryDto> getRewards(String customerId) {
        //Get Customer
        Customer customer = getCustomer(customerId);
        // Check either customer is available with that id
       assert customer != null;
       //Get a list of Customer Cash back history
        List<CashbackHistory> history = customer.getCashbackHistory();
        return history.stream().map(this::historyDto).toList();
    }

    // Generate token
    public TokenResponseDto getToken(TokenRequestDto customerid) {
        //GET customer
        Customer customer = getCustomer(customerid.getCustomerId());
        System.out.println(customer);
        assert customer != null;
        String token = jwtToken.generateToken(customerid.getCustomerId());

        return  new TokenResponseDto(token);


    }

    //Get Total balance
    public CustomerDto customerReward() {
       String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = getCustomer(principal);
        assert customer != null;
        return mapToCustomerDto(customer);
    }
    //Mapping to Customer
    private CustomerDto mapToCustomerDto(Customer customer) {
       return CustomerDto.builder()
                .currentBalance(customer.getCurrentBalance())
                .totalCashback(customer.getTotalCashback())
                .customerId(customer.getCustomerId())
                .build();
    }
    //Mapping to reword
    private RewardHistoryDto historyDto(CashbackHistory cashbackHistory) {
        return RewardHistoryDto.builder()
                .transactionId(cashbackHistory.getTransactionId())
                .amountEarned(cashbackHistory.getAmount())
                .transactionDate(cashbackHistory.getTransactionDate())
                .description(cashbackHistory.getDescription())
                .build();
    }
}
