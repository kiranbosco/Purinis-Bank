package com.coding.exercise.bankapp.controller;

import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.service.BankingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private BankingServiceImpl bankingServiceImpl;

    @GetMapping("/all")
    public List<CustomerDetails> getAllCustomerDetails() {
        return bankingServiceImpl.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDetails customer) {
        return this.bankingServiceImpl.addCustomer(customer);
    }  
}
