package com.app.rewards_system.repository;

import com.app.rewards_system.model.CashbackHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashBackHistoryRepository extends JpaRepository<CashbackHistory, Integer> {

    Optional<CashbackHistory> findByTransactionId(String transactionId);
}
