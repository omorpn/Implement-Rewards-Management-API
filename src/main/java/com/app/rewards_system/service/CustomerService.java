package com.app.rewards_system.service;

import com.app.rewards_system.dto.CustomerDto;
import com.app.rewards_system.dto.RewardHistoryDto;
import com.app.rewards_system.model.CashbackHistory;
import com.app.rewards_system.model.Customer;
import com.app.rewards_system.repository.CashBackHistoryRepository;
import com.app.rewards_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CashBackHistoryRepository cashBackHistoryRepository;

    public CustomerService(CustomerRepository customerRepository, CashBackHistoryRepository cashBackHistoryRepository) {
        this.customerRepository = customerRepository;
        this.cashBackHistoryRepository = cashBackHistoryRepository;
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
    // Get Single Reward History
    public RewardHistoryDto getReward(String transactionId) {
        //GET customer
      CashbackHistory history = cashBackHistoryRepository.findByTransactionId(transactionId).orElseThrow(()-> new IllegalArgumentException("Transaction not found"));
        return historyDto(history);

    }

    //Get Total Reward
    public CustomerDto customerReward(String customerId) {
        Customer customer = getCustomer(customerId);
        assert customer != null;
        return mapToCustomerDto(customer);
    }
    private CustomerDto mapToCustomerDto(Customer customer) {
       return CustomerDto.builder()
                .currentBalance(customer.getCurrentBalance())
                .totalCashback(customer.getTotalCashback())
                .customerId(customer.getCustomerId())
                .build();
    }

    private RewardHistoryDto historyDto(CashbackHistory cashbackHistory) {
        return RewardHistoryDto.builder()
                .transactionId(cashbackHistory.getTransactionId())
                .amountEarned(cashbackHistory.getAmount())
                .transactionDate(cashbackHistory.getTransactionDate())
                .description(cashbackHistory.getDescription())
                .build();
    }
}
