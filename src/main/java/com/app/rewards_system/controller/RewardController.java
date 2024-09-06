package com.app.rewards_system.controller;

import com.app.rewards_system.dto.CustomerDto;
import com.app.rewards_system.dto.RewardHistoryDto;
import com.app.rewards_system.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    private final CustomerService customerService;

    public RewardController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/history/{id}")
    public RewardHistoryDto getRewardHistory(@PathVariable String id) {
       return customerService.getReward(id);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/history/all/{id}")
    public List<RewardHistoryDto> getAllRewardHistory(@PathVariable String id) {
        return customerService.getRewards(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/balance/{id}")
    public CustomerDto getCustomerBalance(@PathVariable String id) {
        return customerService.customerReward(id);
    }
}
