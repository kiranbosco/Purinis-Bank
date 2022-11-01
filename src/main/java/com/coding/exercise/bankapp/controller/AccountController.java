package com.coding.exercise.bankapp.controller;

import com.coding.exercise.bankapp.service.BankingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/accounts")
public class AccountController {
    private BankingServiceImpl bankingService;
    public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber) {

        return bankingService.findByAccountNumber(accountNumber);
    }
}
