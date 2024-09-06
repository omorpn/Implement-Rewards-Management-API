package com.app.rewards_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer",uniqueConstraints = @UniqueConstraint(name = "customer_id",columnNames = "customer_id"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customer_id")
    private String customerId;
    private double totalCashback;
    private double currentBalance;
    @OneToMany
    private List<CashbackHistory> cashbackHistory;
    private Role role;
}
