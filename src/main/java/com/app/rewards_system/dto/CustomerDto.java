package com.app.rewards_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String customerId;
    private double totalCashback;
    private double currentBalance;
}
