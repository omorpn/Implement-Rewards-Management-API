package com.app.rewards_system.dto;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Builder
@Data

public class RewardHistoryDto {
    /// JSON array of cashback transactions, each containing transactionId, transactionDate, amountEarned, and description
    private String transactionId;
    private LocalDateTime transactionDate;
    private double amountEarned;
    private String description;
}
