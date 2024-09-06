package com.app.rewards_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cash_back_history",uniqueConstraints = @UniqueConstraint(name = "cashback_unique",columnNames = "cashback_id" +
        "cashback_id"))
public class CashbackHistory {
    @Id
    @Column(name = "cashback_id", nullable = false, updatable = false)
    private String transactionId;
    @CreatedDate
    private LocalDateTime transactionDate;
    private double amount;
    private String description;
    @ManyToOne
    private Customer user;

}
