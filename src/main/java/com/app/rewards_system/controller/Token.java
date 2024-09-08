package com.app.rewards_system.controller;

import com.app.rewards_system.dto.TokenRequestDto;
import com.app.rewards_system.dto.TokenResponseDto;
import com.app.rewards_system.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Token {
    private final CustomerService customerService;

    public Token(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/token")
    public TokenResponseDto getToken(@RequestBody TokenRequestDto tokenRequestDto) {
        return customerService.getToken(tokenRequestDto);
    }
}
