package com.coding.exercise.bankapp.controller;

import com.coding.exercise.bankapp.domain.AccountInformation;
import com.coding.exercise.bankapp.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping(path = "/{accountNumber}")
    public AccountInformation getAccountNumber(@PathVariable Long accountNumber) throws Exception {
        return this.bankingService.findByAccountNumber(accountNumber);
    }

    @PostMapping(path = "/add/{customerNumber}")
    public ResponseEntity<Object> addNewAccount(@RequestBody AccountInformation accountInformation,
                                                @PathVariable Long customerNumber) {

        return bankingService.addNewAccount(accountInformation, customerNumber);
    }
}
