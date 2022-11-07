package com.coding.exercise.bankapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coding.exercise.bankapp.domain.CustomerDetails;
import com.coding.exercise.bankapp.service.BankingServiceImpl;


@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping(path = "/all")
    public List<CustomerDetails> getAllCustomers() throws Exception {
        return bankingService.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDetails customer) throws Exception {
        return bankingService.addCustomer(customer);
    }

    @GetMapping(path = "/{customerNumber}")
    public CustomerDetails getCustomer(@PathVariable Long customerNumber) throws Exception {

        return bankingService.findByCustomerNumber(customerNumber);
    }
}
